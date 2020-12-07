package pl.jbobbinprinter;

import java.util.Map;

public interface PrinterMain {

  /**
   *
   * @param key map key
   * @param yarn Yarn object
   * @param listToAdd Map object
   */
  void addToPrintList(int key, Yarn yarn, Map<Integer, Yarn> listToAdd);

  /**
   *
   * @param key map key
   * @param listToRemove Mapobject
   */
  void removeFromPrintList(int key, Map<Integer, Yarn> listToRemove);



}
