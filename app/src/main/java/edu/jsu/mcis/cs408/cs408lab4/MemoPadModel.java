package edu.jsu.mcis.cs408.cs408lab4;

import android.util.Log;

public class MemoPadModel extends AbstractModel {

    private final MemoPadDatabaseHandler db;

    public static final String TAG = "DefaultModel";

    public MemoPadModel(MainActivity activity) {

        db = new MemoPadDatabaseHandler(activity, null, null, 1);

    }

    public void addMemo(String newText) {

        Memo m = new Memo(newText);

        db.addMemo(m);

        firePropertyChange(MemoPadController.MEMO_LIST_PROPERTY, null, db.getAllMemosAsList());

    }

    public void deleteMemo(int id) {
        db.deleteMemo(id);
        firePropertyChange(MemoPadController.MEMO_LIST_PROPERTY, null, db.getAllMemosAsList());
    }

    public void getAllMemos() {

        firePropertyChange(MemoPadController.MEMO_LIST_PROPERTY, null, db.getAllMemosAsList());

    }

}
