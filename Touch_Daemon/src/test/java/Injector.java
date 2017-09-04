
import java.io.File;
import touchdaemon.DayTrigger;

/*
 * Copyright (C) 2017 Ivan
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

/**
 *
 * @author Ivan
 */
public class Injector {
    
    public static void main(String[] args) {
        DayTrigger dt = new DayTrigger("", "", true, true);
        ///dt.injectToPar(new File("P_REGPAR.DAT141-142-143-144-145-146"), new File("P_141PAR.DAT"));
        dt.injectToRef(new File("S_PLUREF_SRC.DAT"), new File("S_PLUREF.DAT"));
    }
}
