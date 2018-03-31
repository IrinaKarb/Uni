import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;



public class CreateTable {
    private static String word;
    private static int currentRow;
    private static int currentColumn;
    public static int columnsNum;
    public static int rowsNum;
    public static boolean right = false;
    public static boolean down = true;
    public static boolean stop = false;
    public static TableItem[] tableItems;

    private void createElements(){

    }

    public void create(Shell shell, Composite checkBoxButtons){
        Group group = new Group(shell, SWT.NONE);
        group.setText("Table");
        Composite taskTextAndButtons = new Composite(group, SWT.NONE);
        taskTextAndButtons.setLayout(new FillLayout(SWT.VERTICAL));
        MessageBox messageBox = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("Warning!");
        messageBox.setMessage("Select the text.");
        Text text = new Text(taskTextAndButtons, SWT.BORDER);
        Button submitButton = new Button(taskTextAndButtons, SWT.PUSH);
        submitButton.setText("Submit");
        Label currentCell = new Label(taskTextAndButtons, SWT.HORIZONTAL);
        Button firstColumnButton = new Button(taskTextAndButtons, SWT.PUSH);
        firstColumnButton.setText("To First Column");
        Button fromFirstToSecondButton = new Button(taskTextAndButtons, SWT.PUSH);
        fromFirstToSecondButton.setText("From 1 to 2");
        Button fromSecondToFirstButton = new Button(taskTextAndButtons, SWT.PUSH);
        fromSecondToFirstButton.setText("From 2 to 1");
        Button createTable = new Button(taskTextAndButtons, SWT.PUSH);
        createTable.setText("Create Table");
        Composite sizeTable = new Composite(group, SWT.NONE);
        Label labelColumns = new Label(sizeTable, SWT.HORIZONTAL);
        labelColumns.setText("Columns: ");
        Text textColumns = new Text(sizeTable, SWT.BORDER);
        Label labelRows = new Label(sizeTable, SWT.HORIZONTAL);
        labelRows.setText("Rows: ");
        Text textRows = new Text(sizeTable, SWT.BORDER);
        GridLayout gridLayout = new GridLayout();
        Button startButton = new Button(sizeTable, SWT.PUSH);
        startButton.setText("Start");
        Button stopButton = new Button(sizeTable, SWT.PUSH);
        stopButton.setText("Stop");
        gridLayout.numColumns = 2;
        sizeTable.setLayout(gridLayout);
        group.setLayout(new FillLayout(SWT.VERTICAL));
        FormData data = new FormData();
        data.left = new FormAttachment(checkBoxButtons, 0);
        group.setLayoutData(data);
        createTable.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (textColumns.getText().equals("") || textRows.getText().equals("")) {
                    MessageBox errorRowsColumns = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.OK);
                    errorRowsColumns.setText("Warning!");
                    errorRowsColumns.setMessage("Enter column or row.");
                    errorRowsColumns.open();
                } else {
                    columnsNum = Integer.parseInt(textColumns.getText());
                    rowsNum = Integer.parseInt(textRows.getText());
                    textColumns.setText("");
                    textRows.setText("");
                    Table table = new Table(group, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
                    table.setLinesVisible(true);
                    table.setHeaderVisible(true);
                    for (int i = 0; i < columnsNum; i++) {
                        TableColumn column = new TableColumn(table, SWT.NONE);
                        column.setText("Column " + (i + 1));
                        column.setWidth(100);
                    }
                    for (int i = 0; i <rowsNum; i++) {
                        TableItem tableItem = new TableItem(table, SWT.NONE);
                    }
                    TableItem[] tableItems = table.getItems();
                    Rectangle clientArea = shell.getClientArea();
                    table.setLocation(clientArea.x, clientArea.y);
                    table.addListener(SWT.MouseDown, event -> {
                        Point pt = new Point(event.x, event.y);
                        TableItem item = table.getItem(pt);
                        if (item == null)
                            return;
                        for (int i = 0; i < columnsNum; i++) {
                            Rectangle rect = item.getBounds(i);
                            if (rect.contains(pt)) {
                                currentRow = table.indexOf(item);
                                currentCell.setText("Row " + (currentRow + 1) + " - " + "Column " + (i + 1));
                                currentColumn = i;
                            }
                        }
                    });
                    submitButton.addSelectionListener(new SelectionListener() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {
                            tableItems[currentRow].setText(currentColumn, text.getText());
                            text.setText("");
                        }

                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                        }
                    });
                    shell.pack();
                    group.pack();
                    firstColumnButton.addSelectionListener(new SelectionListener() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {
                            if (!text.getText().isEmpty()) {
                                tableItems[0].setText(0, text.getText());
                                text.setText("");
                                word = tableItems[0].getText();
                            }
                        }

                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                        }
                    });
                    fromFirstToSecondButton.addSelectionListener(new SelectionListener() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {
                            if (!tableItems[0].getText().isEmpty()) {
                                if (table.getSelectionIndex() == 0) {
                                    tableItems[0].setText(0, "");
                                    tableItems[0].setText(1, word);
                                } else
                                    messageBox.open();
                            }
                        }

                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                        }
                    });
                    fromSecondToFirstButton.addSelectionListener(new SelectionListener() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {
                            if (!tableItems[0].getText().isEmpty()) {
                                if (table.getSelectionIndex() == 0) {
                                    tableItems[0].setText(1, "");
                                    tableItems[0].setText(0, word);
                                } else
                                    messageBox.open();
                            }
                        }

                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                        }
                    });

                    startButton.addSelectionListener(new SelectionListener() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {

                            MoveItems moveItems = new MoveItems();
                            moveItems.magic(tableItems);
                        }

                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {

                        }
                    });

                    stopButton.addSelectionListener(new SelectionListener() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {
                            stop = true;
                        }

                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {

                        }
                    });


                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }

        });




    }
}
