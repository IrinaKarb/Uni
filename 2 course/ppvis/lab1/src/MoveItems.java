import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class MoveItems extends CreateTable {
    private boolean isMovedRight;
    private boolean isMovedDown;
    private int currentColumn;
    private int currentRow;
    private String currentWord;

    public void magic(TableItem[] tableItems){
        (new Thread(new Runnable() {
            @Override
            public void run() {

                do {
                    Display.getDefault().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            for (int it = (arrayList.size() - 1); it >= 0; it--) {
                                if (!arrayList.get(it).getRight()) {
                                    if (arrayList.get(it).getCurrentColumn() == (columnsNum - 1)) {
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(0, arrayList.get(it).getCurrentWord());
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn(), "");

                                        changeItem(it, arrayList.get(it).getDown(), arrayList.get(it).getCurrentRow(), 0);

                                        continue;
                                    }
                                    if (checkCell(it) != -1) {

                                        changeItem(it, arrayList.get(it).getDown());
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn() + 1, arrayList.get(it).getCurrentWord() + ", " + arrayList.get(checkCell(it)).getCurrentWord());
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn(), "");
                                        continue;

                                    }
                                    tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn() + 1, arrayList.get(it).getCurrentWord());
                                    tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn(), "");
                                    changeItem(it, arrayList.get(it).getDown());
                                    continue;
                                }

                                if (!arrayList.get(it).getDown()) {
                                    if (arrayList.get(it).getCurrentRow() == (rowsNum - 1)) {
                                        tableItems[0].setText(arrayList.get(it).getCurrentColumn(), arrayList.get(it).getCurrentWord());
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn(), "");
                                        changeItem(it, arrayList.get(it).getDown(), 0, arrayList.get(it).getCurrentColumn());
                                        continue;
                                    }
                                    if (checkCell(it) != -1) {

                                        changeItem(it, arrayList.get(it).getDown());
                                        tableItems[arrayList.get(it).getCurrentRow() + 1].setText(arrayList.get(it).getCurrentColumn(), arrayList.get(it).getCurrentWord() + ", " + arrayList.get(checkCell(it)).getCurrentWord());
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn(), "");
                                        continue;

                                    }
                                    tableItems[arrayList.get(it).getCurrentRow() + 1].setText(arrayList.get(it).getCurrentColumn(), arrayList.get(it).getCurrentWord());
                                    tableItems[arrayList.get(it).getCurrentRow()].setText(arrayList.get(it).getCurrentColumn(), "");
                                    changeItem(it, arrayList.get(it).getDown());
                                    continue;
                                }

                            }

                        }

                    });
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while(!stop);
            }
        })).start();
    }

    MoveItems(int currentRow, int currentColumn, String currentWord, boolean isMovedRight, boolean isMovedDown){
        this.currentRow = currentRow;
        this.currentColumn = currentColumn;
        this.currentWord = currentWord;
        this.isMovedRight = isMovedRight;
        this.isMovedDown = isMovedDown;
    }

    public static void changeItem(int it, boolean isMovedDown){

        if (isMovedDown)
            moveItems = new MoveItems(arrayList.get(it).getCurrentRow(), arrayList.get(it).getCurrentColumn() + 1, arrayList.get(it).getCurrentWord(), true, false);
        else moveItems = new MoveItems(arrayList.get(it).getCurrentRow() + 1, arrayList.get(it).getCurrentColumn(), arrayList.get(it).getCurrentWord(), false, true);

        arrayList.remove(it);
        arrayList.add(it, moveItems);
    }

    public static void changeItem(int it, boolean isMovedDown, int row, int column) {
        if (isMovedDown)
            moveItems = new MoveItems(row, column, arrayList.get(it).getCurrentWord(), true, false);
        else moveItems = new MoveItems(row, column, arrayList.get(it).getCurrentWord(), false, true);

        arrayList.remove(it);
        arrayList.add(it, moveItems);
    }

    public static int checkCell(int currentIt) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (i != currentIt) {
                if (arrayList.get(i).getCurrentRow() == arrayList.get(currentIt).getCurrentRow() && arrayList.get(i).getCurrentColumn() == arrayList.get(currentIt).getCurrentColumn())
                    return i;
            }
        }
        return -1;

    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public boolean getRight() {
        return isMovedRight;
    }

    public boolean getDown() {
        return isMovedDown;
    }
}
