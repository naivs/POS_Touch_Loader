/*
 * Copyright (C) 2017 Ivan Naumov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package touchdaemon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Ivan
 */
public class DayTrigger {

    private String path;
    private boolean parSettings;
    private boolean refSettings;

    public DayTrigger(String path, String firedTime, boolean parSettings, boolean refSettings) {
        this.path = path;
        this.parSettings = parSettings;
        this.refSettings = refSettings;

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        while (true) {
            Date actualTime = new Date(System.currentTimeMillis());

            if (df.format(actualTime).equals(firedTime)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(actualTime);

                int dayOfWeek = 0;
                // check config on day
                switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                    case Calendar.MONDAY:
                        dayOfWeek = 0;
                        break;
                    case Calendar.TUESDAY:
                        dayOfWeek = 1;
                        break;
                    case Calendar.WEDNESDAY:
                        dayOfWeek = 2;
                        break;
                    case Calendar.THURSDAY:
                        dayOfWeek = 3;
                        break;
                    case Calendar.FRIDAY:
                        dayOfWeek = 4;
                        break;
                    case Calendar.SATURDAY:
                        dayOfWeek = 5;
                        break;
                    case Calendar.SUNDAY:
                        dayOfWeek = 6;
                        break;
                }

                File day = new File(this.path + "/d_" + (dayOfWeek));

                if (day.exists()) {
                    TouchDaemon.LOGGER.log(Level.INFO, "{0} exists. Loading...", day.getName());
                    loadToServer(day);
                } else {
                    TouchDaemon.LOGGER.log(Level.INFO, "{0} not exists.", day.getName());
                }
            }
        }
    }

    private void loadToServer(File loadDay) {
        // load images
        try {
            File[] images = new File(loadDay.getCanonicalPath() + "/cafe/").listFiles();
            for(File image : images) {
                Files.copy(image.toPath(), new File(TouchDaemon.IMAGES + image.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Unable to copy any of ../cafe/image file... ", ex.getMessage());
        } 
        
        // load REGPAR.DATs
        FilenameFilter parFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("P_REGPAR.DAT");
            }
        };

        File[] source = loadDay.listFiles(parFilter);
        for (File src : source) {
            String[] names = getRegParNames(src.getName());
            for (String name : names) {
                File dest = new File(TouchDaemon.SERVER_PATH + name);
                injectToPar(src, dest);
                try {
                    Files.copy(dest.toPath(), new File(TouchDaemon.SERVER_PATH_LAN + dest.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(dest.toPath(), new File(TouchDaemon.SERVER_PATH_LAN4SRV + dest.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    if(parSettings) {
                        Files.copy(dest.toPath(), new File(TouchDaemon.HOC_PATH + dest.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException ex) {
                    TouchDaemon.LOGGER.log(Level.SEVERE, "Unable to copy REGPAR.DAT file... ", ex.getMessage());
                }
            }
        }
        
        // load PLUREF.DAT
        try {
            File pluPef = new File(TouchDaemon.SERVER_PATH + "S_PLUREF.DAT");
            injectToRef(new File(loadDay.getCanonicalPath() + "S_PLUREF.DAT"), pluPef);
            //Files.copy(new File(loadDay.getCanonicalPath() + "S_PLUREF.DAT").toPath(), new File(TouchDaemon.SERVER_PATH + "S_PLUREF.DAT").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(pluPef.toPath(), new File(TouchDaemon.SERVER_PATH_LAN + "S_PLUREF.DAT").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(pluPef.toPath(), new File(TouchDaemon.SERVER_PATH_LAN4SRV + "S_PLUREF.DAT").toPath(), StandardCopyOption.REPLACE_EXISTING);
            if(refSettings) {
                Files.copy(pluPef.toPath(), new File(TouchDaemon.HOC_PATH + "S_PLUREF.DAT").toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Unable to copy PLUREF.DAT file... ", ex.getMessage());
        }

        // web load
        try {
            if (parSettings || refSettings) {
                File key = new File(TouchDaemon.HOC_PATH + TouchDaemon.WEB_KEYFILE);
                File web = new File(TouchDaemon.WEB_PATH);
                
                for(File f : web.listFiles()) {
                    f.delete();
                }
                
                key.createNewFile();
            }
        } catch (IOException ex) {
            TouchDaemon.LOGGER.log(Level.WARNING, "I/O Exception while writing files to " + TouchDaemon.HOC_PATH, ex.getMessage());
        }
    }

    private String[] getRegParNames(String name) {
        String[] out = new String[name.substring(12).split("-").length];

        for (int i = 0; i < out.length; i++) {
            out[i] = "P_" + name.substring(12).split("-")[i] + "PAR.DAT";
        }
        return out;
    }

    private void injectToPar(File source, File dest) {
        String srcData = "";
        String destData = "";
        
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), "Cp866"));
            String buf;

            while ((buf = reader.readLine()) != null) {
                srcData += buf;
            }
        } catch (IOException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Can't read data for inject...", e.getMessage());
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                TouchDaemon.LOGGER.log(Level.SEVERE, "Stream unable to close...", e.getMessage());
            }
        }
        
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(dest), "Cp866"));
            String buf;

            while ((buf = reader.readLine()).startsWith("PD")) {
                destData += buf;
            }
            
            destData += srcData;
            
            while ((buf = reader.readLine()) != null) {
                if(!buf.startsWith("PD") && !buf.startsWith("PRES"))
                    destData += buf;
            }
        } catch (IOException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Can't read REGPAR.DAT...", e.getMessage());
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                TouchDaemon.LOGGER.log(Level.SEVERE, "Stream unable to close...", e.getMessage());
            }
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "Cp866"));
                writer.append(destData);
        } catch (IOException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "IO Error while injecting data to server REGPAR.DAT...", e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                TouchDaemon.LOGGER.log(Level.SEVERE, "Stream unable to close...", e.getMessage());
            }
        }
    }
    
    private void injectToRef(File source, File dest) {
        String srcData = "";
        String destData = "";
        
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        String startIndex = null, endIndex = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), "Cp866"));
            String buf;

            int i = 0;
            while ((buf = reader.readLine()) != null) {
                srcData += buf;
                if(i == 0) startIndex = buf.substring(1, 4);
            }
            endIndex = buf.substring(1, 4);
        } catch (IOException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Can't read data for inject...", e.getMessage());
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                TouchDaemon.LOGGER.log(Level.SEVERE, "Stream unable to close...", e.getMessage());
            }
        }
        
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(dest), "Cp866"));
            String buf;

            while (Integer.parseInt((buf = reader.readLine()).substring(1, 4)) < Integer.parseInt(startIndex)) {
                destData += buf;
            }
            
            destData += srcData;
            
            while ((buf = reader.readLine()) != null) {
                if(Integer.parseInt(buf.substring(1, 4)) > Integer.parseInt(endIndex))
                    destData += buf;
            }
        } catch (IOException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Can't read PLUREF.DAT...", e.getMessage());
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                TouchDaemon.LOGGER.log(Level.SEVERE, "Stream unable to close...", e.getMessage());
            }
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "Cp866"));
                writer.append(destData);
        } catch (IOException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "IO Error while injecting data to server PLUREF.DAT...", e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                TouchDaemon.LOGGER.log(Level.SEVERE, "Stream unable to close...", e.getMessage());
            }
        }
    }
}
