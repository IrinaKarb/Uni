import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class MoveItems extends CreateTable {
    private boolean isMovedRight;
    private boolean isMovedDown;
    private int currentColumn;
    private int currentRow;
    private String currentWord;
    //private MoveItems[] moveItems = new MoveItems[tableItems.length];

    MoveItems(){
        isMovedRight = false;
        isMovedDown = true;
    }

    public void magic(TableItem[] tableItems){
        (new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    Display.getDefault().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < columnsNum; i++) {
                                for (int j = 0; j < rowsNum; j++) {



                                    if (!tableItems[j].getText(i).equals("")) {
                                        String curWord = tableItems[j].getText(i);
                                        if (!right) {
                                            if (!tableItems[j].getText(i + 1).equals("")) {
                                                tableItems[j].setText(i + 2, tableItems[j].getText(i + 1));
                                                tableItems[j].setText(i + 1, "");
                                            }
                                            if (i == (columnsNum - 1)) {
                                                tableItems[j].setText(0, curWord);
                                                tableItems[j].setText(i, "");
                                                //i = 0;
                                                right = true;
                                                down = false;
                                                return;
                                            }
                                            tableItems[j].setText(i + 1, curWord);
                                            tableItems[j].setText(i, "");
                                            right = true;
                                            down = false;
                                            return;
                                        }
                                        if (!down) {
                                            if (j == (rowsNum - 1)) {
                                                tableItems[0].setText(i, curWord);
                                                tableItems[j].setText(i, "");
                                                //i = 0;
                                                down = true;
                                                right = false;
                                                return;
                                            }
                                            tableItems[j + 1].setText(i, curWord);
                                            tableItems[j].setText(i, "");
                                            down = true;
                                            right = false;
                                            return;
                                        }
                                    }

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

    public void moveRight(TableItem[] tableItems, int column, int row){
        if (!tableItems[row].getText().equals("")) {
            String curWord = tableItems[row].getText(column);
            System.out.println(curWord);
        /*if (!tableItems[row].getText(column + 1).equals("")) {
            tableItems[row].setText(column + 2, tableItems[j].getText(i + 1));
            tableItems[j].setText(i + 1, "");
        }*/
            if (column == (columnsNum - 1)) {
                tableItems[row].setText(0, curWord);
                tableItems[row].setText(column, "");
                return;
            }
            tableItems[row].setText(column + 1, curWord);
            tableItems[row].setText(column, "");
            return;
        }
    }

    public void moveDown(){

    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    /*public void setMoveItems() {
        for (int i = 0; i < moveItems.length; i++)
            moveItems[i] = new MoveItems();
    }*/
}
