package edu.jsu.mcis.cs408.cs408lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.beans.PropertyChangeEvent;
import java.util.List;

import edu.jsu.mcis.cs408.cs408lab4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AbstractView {

    public static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    private MemoPadController controller;

    private final MemoPadButtonClickHandler buttonClick = new MemoPadButtonClickHandler();

    private final MemoPadItemClickHandler itemClick = new MemoPadItemClickHandler();

    private int selectedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* Create Controller and Model */

        controller = new MemoPadController(new MemoPadModel(this));

        /* Register Activity View with Controller */

        controller.addView(this);

        /* Associate Click Handler with Input Buttons */

        ConstraintLayout layout = binding.layout;

        binding.addButton.setOnClickListener(buttonClick);
        binding.deleteButton.setOnClickListener(buttonClick);

        /* Get Initial Memo Database Contents */

        controller.getAllMemos();

    }

    public MemoPadItemClickHandler getItemClick() { return itemClick; }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {

        String propertyName = evt.getPropertyName();

        if ( propertyName.equals(MemoPadController.MEMO_LIST_PROPERTY) ) {

            List<Memo> data = (List)evt.getNewValue();

            Log.i(TAG, "Number of Memos: " + data.size());

            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, data);
            binding.output.setHasFixedSize(true);
            binding.output.setLayoutManager(new LinearLayoutManager(this));
            binding.output.setAdapter(adapter);

        }

    }

    private class MemoPadButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            String tag = view.getTag().toString();

            switch (tag) {

                case "addButton":
                    String newText = binding.textInput.getText().toString();
                    controller.addMemo(newText);
                    break;

                case "deleteButton":
                    controller.deleteMemo(selectedId);
                    break;

            }

        }

    }

    private class MemoPadItemClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = binding.output.getChildLayoutPosition(v);
            RecyclerViewAdapter adapter = (RecyclerViewAdapter)binding.output.getAdapter();
            if (adapter != null) {
                Memo memo = adapter.getItem(position);
                selectedId = memo.getId();
                Toast.makeText(v.getContext(), String.valueOf(selectedId), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
