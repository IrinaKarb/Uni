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

import java.util.ArrayList;


public class CreateTable {
    private static Group group;
    private static Composite taskTextAndButtons;
    private static Composite sizeTable;
    private static MessageBox messageBox;
    private static Table table;
    private static Text text;
    private static Text textColumns;
    private static Text textRows;
    private static Button submitButton;
    private static Button firstColumnButton;
    private static Button fromFirstToSecondButton;
    private static Button fromSecondToFirstButton;
    private static Button createTable;
    private static Button startButton;
    private static Button stopButton;
    private static Label currentCell;
    private static Label labelColumns;
    private static Label labelRows;
    private static GridLayout gridLayout;
    private static FormData data;
    private static String word;
    private static int currentRow;
    private static int currentColumn;
    public static int columnsNum;
    public static int rowsNum;
    public static boolean stop;
    public static TableItem[] tableItems;
    public static MoveItems moveItems;
    public static ArrayList<MoveItems> arrayList;

    private static void createElements(Shell shell){
        group = new Group(shell, SWT.NONE);
        taskTextAndButtons = new Composite(group, SWT.NONE);
        messageBox = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.OK);
        text = new Text(taskTextAndButtons, SWT.BORDER);
        submitButton = new Button(taskTextAndButtons, SWT.PUSH);
        currentCell = new Label(taskTextAndButtons, SWT.HORIZONTAL);
        firstColumnButton = new Button(taskTextAndButtons, SWT.PUSH);
        fromFirstToSecondButton = new Button(taskTextAndButtons, SWT.PUSH);
        fromSecondToFirstButton = new Button(taskTextAndButtons, SWT.PUSH);
        createTable = new Button(taskTextAndButtons, SWT.PUSH);
        sizeTable = new Composite(group, SWT.NONE);
        labelColumns = new Label(sizeTable, SWT.HORIZONTAL);
        textColumns = new Text(sizeTable, SWT.BORDER);
        labelRows = new Label(sizeTable, SWT.HORIZONTAL);
        textRows = new Text(sizeTable, SWT.BORDER);
        startButton = new Button(sizeTable, SWT.PUSH);
        stopButton = new Button(sizeTable, SWT.PUSH);
        data = new FormData();
    }

    public static void setFifthTaskElements() {
        group.setText("Table");
        taskTextAndButtons.setLayout(new FillLayout(SWT.VERTICAL));
        messageBox.setText("Warning!");
        messageBox.setMessage("Select the text.");
        submitButton.setText("Submit");
        firstColumnButton.setText("To First Column");
        fromFirstToSecondButton.setText("From 1 to 2");
        fromSecondToFirstButton.setText("From 2 to 1");
    }

    public void create(Shell shell, Composite checkBoxButtons){
        CreateTable.createElements(shell);
        CreateTable.setFifthTaskElements();
        createTable.setText("Create Table");
        labelColumns.setText("Columns: ");
        labelRows.setText("Rows: ");
        gridLayout = new GridLayout();
        startButton.setText("Start");
        stopButton.setText("Stop");
        gridLayout.numColumns = 2;
        sizeTable.setLayout(gridLayout);
        group.setLayout(new FillLayout(SWT.VERTICAL));
        data.left = new FormAttachment(checkBoxButtons, 0);
        group.setLayoutData(data);
        arrayList = new ArrayList<>();
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
                    table = new Table(group, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
                    table.setLinesVisible(true);
                    for (int i = 0; i < columnsNum; i++) {
                        TableColumn column = new TableColumn(table, SWT.NONE);
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
                            moveItems = new MoveItems(currentRow, currentColumn, text.getText(), false, true);
                            text.setText("");
                            arrayList.add(moveItems);
                        }
                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                        }
                    });
                    shell.pack();
                    group.pack();
                    table.pack();
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
                            stop = false;
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
