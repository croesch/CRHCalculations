package com.github.crhcalculations.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Comment here ???
 * 
 * @author $Author: Christian $
 * @version $Revision: 1.1 $ ($Date: 25.07.2010 16:38:47 $)
 */
public class TwinPrimeFinder {
  /** Version number. */
  public static final String VER = "$Revision: 1.1 $";

  private final List<Integer> primzahlen = new ArrayList<Integer>();

  public static void main(String[] args) {
    TwinPrimeFinder pz = new TwinPrimeFinder();
    pz.initPrimzahlen(100000);
    int primOld = -12;
    for (int prim : pz.getPrimzahlen()) {
      if (prim - primOld == 2) {
        System.out.println(primOld + " - " + prim);
      }
      primOld = prim;
    }
  }

  public List<Integer> getPrimzahlen() {
    return this.primzahlen;
  }

  private void initPrimzahlen(int max) {
    for (int i = 2; i <= max; ++i) {
      if (this.primzahlen.size() == 0) {
        this.primzahlen.add(i);
      } else {
        boolean neuePrim = true;
        for (int prim : this.primzahlen) {
          if (i % prim == 0) {
            neuePrim = false;
            break;
          }
        }
        if (neuePrim) {
          this.primzahlen.add(i);
        }
      }
    }
  }
}
