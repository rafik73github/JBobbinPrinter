package pl.jbobbinprinter;

import java.util.Map;

public class PrinterFunctions implements PrinterMain {


    @Override
    public void addToPrintList(int key, Yarn yarn, Map<Integer, Yarn> listToAdd) {

        listToAdd.put(key,yarn);

    }

    @Override
    public void removeFromPrintList(int key, Map<Integer, Yarn> listToRemove) {
        listToRemove.remove(key);
    }
}
