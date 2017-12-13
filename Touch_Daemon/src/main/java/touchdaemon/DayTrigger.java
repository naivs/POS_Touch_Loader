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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import logger.CustomLogger;
import services.LoggerService;

/**
 *
 * @author Ivan
 */
public class DayTrigger extends Observable {

    private final String firedTime;
    private final String path;
    private final boolean parSettings;
    private final boolean refSettings;

    private final Timer timer;

    private File day;

    public DayTrigger(String path, String firedTime, boolean parSettings, boolean refSettings) {
        this.path = path;
        this.firedTime = firedTime;
        this.parSettings = parSettings;
        this.refSettings = refSettings;
        timer = new Timer(true);
    }

    public void start() {
        Calendar fire = Calendar.getInstance();
        fire.set(Calendar.HOUR_OF_DAY, Integer.parseInt(firedTime.split(":")[0]));
        fire.set(Calendar.MINUTE, Integer.parseInt(firedTime.split(":")[1]));

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                upload(false);
            }
        }, fire.getTime(), 86400000L);
        LoggerService.getLogger().log(CustomLogger.INFO, String.format("trigger started! Upload time: %s", firedTime));
    }

    public String getInfoStatus() {
        String status = "UPLOAD TIME: " + firedTime + "\n"
                + "CURRENT DAY: ";

        if (day != null) {
            switch (Integer.parseInt(day.getName().substring(day.getName().length() - 1))) {
                case 0:
                    status += "Monday";
                    break;

                case 1:
                    status += "Thuesday";
                    break;

                case 2:
                    status += "Wednesday";
                    break;

                case 3:
                    status += "Thursday";
                    break;

                case 4:
                    status += "Friday";
                    break;

                case 5:
                    status += "Saturday";
                    break;

                case 6:
                    status += "Sunday";
                    break;

                default:
                    status += "---";
            }
        } else {
            status += "---";
        }

        return status;
    }

    public void upload(boolean isHot) {
        LoggerService.getLogger().log(CustomLogger.INFO, "Data upload initialized!");

        int dayOfWeek;
        // check config on day
        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                dayOfWeek = 1;
                break;
            case Calendar.TUESDAY:
                dayOfWeek = 2;
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = 3;
                break;
            case Calendar.THURSDAY:
                dayOfWeek = 4;
                break;
            case Calendar.FRIDAY:
                dayOfWeek = 5;
                break;
            case Calendar.SATURDAY:
                dayOfWeek = 6;
                break;
            case Calendar.SUNDAY:
                dayOfWeek = 0;
                break;
            default:
                dayOfWeek = 0;
        }

        if (isHot) {
            dayOfWeek = dayOfWeek == 0 ? 6 : dayOfWeek - 1;
            setChanged();
        }

        day = new File(path + "/day" + (dayOfWeek));
        if (day.exists()) {
            LoggerService.getLogger().log(CustomLogger.INFO, String.format("%s exists. Loading...", day.getName()));
            loadToServer(day);
            LoggerService.getLogger().log(CustomLogger.INFO, String.format("Day %s uploaded!", day.getName()));
            if (isHot) {
                notifyObservers(0);
            }
        } else {
            LoggerService.getLogger().log(CustomLogger.INFO, String.format("%s not exists!", day.getName()));
            if (isHot) {
                notifyObservers(1);
            }
        }
        // load static departments
        day = new File(path + "/static");
        if (day.exists()) {
            LoggerService.getLogger().log(CustomLogger.INFO, String.format("%s exists. Loading...", day.getName()));
            loadToServer(day);
            LoggerService.getLogger().log(CustomLogger.INFO, String.format("Day %s uploaded!", day.getName()));
        } else {
            LoggerService.getLogger().log(CustomLogger.INFO, String.format("%s not exists!", day.getName()));
        }

        LoggerService.getLogger().log(CustomLogger.INFO, "Waiting for the next upload...\n");
    }

    private void copyFile(File source, File dest) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            if (input != null && output != null) {
                input.close();
                output.close();
            }
        }
    }

    private void loadToServer(File loadDay) {
        // load images
        try {
            File[] images = new File(loadDay.getCanonicalPath() + "/cafe/").listFiles();
            for (File image : images) {
                //Files.copy(image.toPath(), new File(TouchDaemon.IMAGES + image.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                copyFile(image, new File(TouchDaemon.IMAGES + image.getName()));
            }
        } catch (IOException ex) {
            LoggerService.getLogger().log(CustomLogger.WARN, "Unable to copy any of ../cafe/image file... ", ex);
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
                    copyFile(dest, new File(TouchDaemon.SERVER_PATH_LAN + dest.getName()));
                    copyFile(dest, new File(TouchDaemon.SERVER_PATH_LAN4SRV + dest.getName()));
                    if (parSettings) {
                        copyFile(dest, new File(TouchDaemon.HOC_PATH + dest.getName()));
                    }
                } catch (IOException ex) {
                    LoggerService.getLogger().log(CustomLogger.WARN, "Unable to copy REGPAR.DAT file... ", ex);
                }
            }
        }

        // load PLUREF.DAT
        FilenameFilter refFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("S_PLUREF.DAT");
            }
        };
        source = loadDay.listFiles(refFilter);
        File pluPef = new File(TouchDaemon.SERVER_PATH + "S_PLUREF.DAT");
        for (File plurefPart : source) {
            injectToRef(plurefPart, pluPef);
        }

        try {
            copyFile(pluPef, new File(TouchDaemon.SERVER_PATH_LAN + "S_PLUREF.DAT"));
            copyFile(pluPef, new File(TouchDaemon.SERVER_PATH_LAN4SRV + "S_PLUREF.DAT"));
            if (refSettings) {
                copyFile(pluPef, new File(TouchDaemon.HOC_PATH + "S_PLUREF.DAT"));
            }
        } catch (IOException ex) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "Unable to copy PLUREF.DAT file... ", ex);
        }

        // web load
        try {
            if (parSettings || refSettings) {
                File key = new File(TouchDaemon.HOC_PATH + TouchDaemon.WEB_KEYFILE);
                File web = new File(TouchDaemon.WEB_PATH);

                File[] webFiles = web.listFiles();
                if (webFiles != null) {
                    for (File f : webFiles) {
                        f.delete();
                    }
                }
                key.createNewFile();
            }
        } catch (IOException ex) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "I/O Exception while writing files to " + TouchDaemon.HOC_PATH, ex);
        }
    }

    private String[] getRegParNames(String name) {
        String[] out = new String[name.substring(12).split("-").length];

        for (int i = 0; i < out.length; i++) {
            out[i] = "P_" + name.substring(12).split("-")[i] + "PAR.DAT";
        }
        return out;
    }

    public void injectToPar(File source, File dest) {
        String srcData = "";
        String destData = "";

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), "Cp866"));
            String buf;

            while ((buf = reader.readLine()) != null) {
                srcData += buf + "\r\n";
            }
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "Can't read data for inject...", e);
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LoggerService.getLogger().log(CustomLogger.CRIT, "Stream unable to close...", e);
            }
        }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(dest), "Cp866"));
            String buf;

            while (!(buf = reader.readLine()).startsWith("PD")) {
                destData += buf + "\r\n";
            }

            destData += srcData;

            while ((buf = reader.readLine()) != null) {
                if (!buf.startsWith("PD") && !buf.startsWith("PRES")) {
                    destData += buf + "\r\n";
                }
            }
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "Can't read REGPAR.DAT...", e);
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LoggerService.getLogger().log(CustomLogger.CRIT, "Stream unable to close...", e);
            }
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "Cp866"));
            writer.append(destData);
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "IO Error while injecting data to server REGPAR.DAT...", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                LoggerService.getLogger().log(CustomLogger.CRIT, "Stream unable to close...", e);
            }
        }
    }

    public void injectToRef(File source, File dest) {
        ArrayList<String> srcData = new ArrayList();
        String destData = "";

        BufferedReader reader = null;
        BufferedWriter writer = null;

        String startIndex = null, endIndex = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), "Cp866"));
            String buf;
            while ((buf = reader.readLine()) != null) {
                srcData.add(buf);
            }
            startIndex = srcData.get(0).substring(1, 4);
            endIndex = srcData.get(srcData.size() - 1).substring(1, 4);
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "Can't read data for inject...", e);
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LoggerService.getLogger().log(CustomLogger.CRIT, "Stream unable to close...", e);
            }
        }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(dest), "Cp866"));
            String buf;

            while (Integer.parseInt((buf = reader.readLine()).substring(1, 4)) < Integer.parseInt(startIndex)) {
                destData += buf + "\r\n";
            }

            for (String string : srcData) {
                destData += string + "\r\n";
            }

            while ((buf = reader.readLine()) != null) {
                if (Integer.parseInt(buf.substring(1, 4)) > Integer.parseInt(endIndex)) {
                    destData += buf + "\r\n";
                }
            }
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "Can't read PLUREF.DAT...", e);
            return;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LoggerService.getLogger().log(CustomLogger.CRIT, "Stream unable to close...", e);
            }
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "Cp866"));
            writer.append(destData);
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "IO Error while injecting data to server PLUREF.DAT...", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                LoggerService.getLogger().log(CustomLogger.CRIT, "Stream unable to close...", e);
            }
        }
    }
}
