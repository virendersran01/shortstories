package com.nickrout.shortstories.ui;

import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.util.Log;

import com.nickrout.shortstories.R;
import com.nickrout.shortstories.model.Choice;
import com.nickrout.shortstories.util.IdUtil;
import com.nickrout.shortstories.util.IntentUtil;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.Collections;

public class AddShowScenarioShortcutActivity extends NoDisplayActivity {

    private static final String TAG = "AddShowScenarioShortcut";

    private Choice mChoice;

    @Override
    protected void performPreFinishOperations() {
        Serializer serializer = new Persister();
        String choiceXml = getIntent().getExtras().getString(IntentUtil.EXTRA_CHOICE_XML);
        try {
            mChoice = serializer.read(Choice.class, choiceXml);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            return;
        }
        addShowScenarioShortcut();
    }

    private void addShowScenarioShortcut() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        ShortcutInfo showScenarioShortcut = new ShortcutInfo.Builder(this, IdUtil.getRandomUniqueShortcutId())
                .setShortLabel(getString(R.string.shortcut_title_show_scenario))
                .setLongLabel(getString(R.string.shortcut_title_show_scenario))
                .setDisabledMessage(getString(R.string.shortcut_disabled_message))
                // TODO: Replace with dedicated 'info' icon
                .setIcon(Icon.createWithBitmap(mChoice.getActionType().getIcon(this)))
                .setIntent(IntentUtil.choice(this, mChoice))
                .setRank(0)
                .build();
        shortcutManager.addDynamicShortcuts(Collections.singletonList(showScenarioShortcut));
    }
}
