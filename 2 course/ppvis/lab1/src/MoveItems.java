import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class MoveItems extends CreateTable {
    private boolean isMovedRight;
    private boolean isMovedDown;
    private int currentColumn;
    private int currentRow;
    private int newRow;
    private int newColumn;
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
                                        newRow = arrayList.get(it).getCurrentRow();
                                        newColumn = 0;
                                    }
                                    else {
                                        newRow = arrayList.get(it).getCurrentRow();
                                        newColumn = arrayList.get(it).getCurrentColumn() + 1;
                                    }
                                    if (checkCell(it, newRow, newColumn) != -1) {
                                        tableItems[newRow].setText(0, arrayList.get(it).getCurrentWord() + ", " + arrayList.get(checkCell(it, newRow, newColumn)).getCurrentWord());
                                        tableItems[newRow].setText(arrayList.get(it).getCurrentColumn(), "");
                                    }
                                    else {
                                        tableItems[newRow].setText(newColumn, arrayList.get(it).getCurrentWord());
                                        tableItems[newRow].setText(arrayList.get(it).getCurrentColumn(), "");
                                    }
                                    changeItem(it, arrayList.get(it).getDown(), newRow, newColumn);
                                    continue;
                                }
                                if (!arrayList.get(it).getDown()) {
                                    if (arrayList.get(it).getCurrentRow() == (rowsNum - 1)) {
                                        newRow = 0;
                                        newColumn = arrayList.get(it).getCurrentColumn();
                                    }
                                    else {
                                        newRow = arrayList.get(it).getCurrentRow() + 1;
                                        newColumn = arrayList.get(it).getCurrentColumn();
                                    }
                                    if (checkCell(it, newRow, newColumn) != -1) {
                                        tableItems[newRow].setText(newColumn, arrayList.get(it).getCurrentWord() + ", " + arrayList.get(checkCell(it, newRow, newColumn)).getCurrentWord());
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(newColumn, "");
                                    }
                                    else {
                                        tableItems[newRow].setText(newColumn, arrayList.get(it).getCurrentWord());
                                        tableItems[arrayList.get(it).getCurrentRow()].setText(newColumn, "");
                                    }
                                    changeItem(it, arrayList.get(it).getDown(), newRow, newColumn);
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

    public static void changeItem(int it, boolean isMovedDown, int row, int column) {
        if (isMovedDown)
            moveItems = new MoveItems(row, column, arrayList.get(it).getCurrentWord(), true, false);
        else moveItems = new MoveItems(row, column, arrayList.get(it).getCurrentWord(), false, true);

        arrayList.remove(it);
        arrayList.add(it, moveItems);
    }


    public static int checkCell(int currentIt, int newRow, int newColumn) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (i != currentIt) {
                if (arrayList.get(i).getCurrentRow() == newRow && arrayList.get(i).getCurrentColumn() == newColumn)
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
