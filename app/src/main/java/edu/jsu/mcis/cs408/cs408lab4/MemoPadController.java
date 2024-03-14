package edu.jsu.mcis.cs408.cs408lab4;

public class MemoPadController extends AbstractController
{

    private final MemoPadModel model;

    public MemoPadController(MemoPadModel model) {
        super();
        this.model = model;
        addModel(model);
    }

    public static final String MEMO_LIST_PROPERTY = "MemoList";

    public void addMemo(String newText) {
        model.addMemo(newText);
    }

    public void deleteMemo(int id) {
        model.deleteMemo(id);
    }

    public void getAllMemos() {
        model.getAllMemos();
    }

}