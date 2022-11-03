package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.character.AttributeCombatAdapter;
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.model.character.talents.Attributes;
import com.kanthin.handbookgenshin.model.character.talents.LabelAndLever;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomCombat {

    private Context context;
    private String[] sParam;

    public CustomCombat(Context context) {
        this.context = context;
    }

    public CustomCombat() {
        sParam = new String[]{"lv1", "lv2", "lv3", "lv4", "lv5", "lv6", "lv7", "lv8", "lv9", "lv10"
                , "lv11", "lv12", "lv13", "lv14", "lv15"};
    }

    public String customStringCombat_Constellation(String skill) {
        String cryo = context.getString(R.string.element_cryo);
        String pyro = context.getString(R.string.element_pyro);
        String electro = context.getString(R.string.element_electro);
        String hydro = context.getString(R.string.element_hydro);
        String anemo = context.getString(R.string.element_anemo);
        String dendro = context.getString(R.string.element_dendro);
        String geo = context.getString(R.string.element_geo);
        String elemental = context.getString(R.string.elemental_bursts);
        //sửa text
        String s = skill.replace("\n", "<br>");
        //sửa màu
        s = s.replace(cryo, "<font color=\"#4878a8\"><b>" + cryo + "</b></font>");
        s = s.replace(pyro, "<font color=\"#bf2818\"><b>" + pyro + "</b></font>");
        s = s.replace("Nguyên Tố Hoả", "<font color=\"#bf2818\"><b>Nguyên Tố Hỏa</b></font>");
        s = s.replace(electro, "<font color=\"#9336b0\"><b>" + electro + "</b></font>");
        s = s.replace(geo, "<font color=\"#B68D07\"><b>" + geo + "</b></font>");
        s = s.replace(hydro, "<font color=\"#0b4dda\"><b>" + hydro + "</b></font>");
        s = s.replace(anemo, "<font color=\"#26a684\"><b>" + anemo + "</b></font>");
        s = s.replace(dendro, "<font color=\"#51810e\"><b>" + dendro + "</b></font>");
        s = s.replace("HP", "<font color=\"#F44336\"><b>HP</b></font>");
        s = s.replace(elemental, "<font color=\"#00E5FF\"><b>" + elemental + "</b></font>");
        //sửa ký tự
        s = s.replace("·", "✦ ");
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.startsWith("**", i)) {
                if (count % 2 == 0) {
                    s = s.substring(0, i) + "<b>" + s.substring(i + 2);
                } else {
                    s = s.substring(0, i) + "</b>" + s.substring(i + 2);
                }
                count++;
            }
        }
        return s;
    }

    public String customStringDescription(String string) {
        string = string.replace("\\n", "<br>");
        return string;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getAttributeCombat1(Character character, ArrayList<LabelAndLever> arrayAttribute, AttributeCombatAdapter adapterAttribute) {
        // lấy các label ra tạo thành item
        arrayAttribute.add(new LabelAndLever("", new ArrayList<>(Arrays.asList(sParam))));
        for (int i = 0; i < character.getTalents().getCombat1().getAttributes().getLabels().size(); i++) {
            String label = character.getTalents().getCombat1().getAttributes().getLabels().get(i);
            String labelCustom = label.substring(0, label.indexOf("|"));
            String param = label.substring(label.indexOf("|"));

            //mỗi label có 15 lv <=> 1 mảng 15 phần tử
            ArrayList<String> arrayLv = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                String lv = getCombat1(character, param, j);
                arrayLv.add(lv);
            }
            arrayAttribute.add(new LabelAndLever(labelCustom, arrayLv));
            adapterAttribute.notifyDataSetChanged();
        }
    }

    private String getCombat1(Character character, String string, int index) {
        return getParamFromAttribute(character.getTalents().getCombat1().getAttributes(), string, index);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getAttributeCombat2(Character character, ArrayList<LabelAndLever> arrayAttribute, AttributeCombatAdapter adapterAttribute) {
        // lấy các label ra tạo thành item
        arrayAttribute.add(new LabelAndLever("", new ArrayList<>(Arrays.asList(sParam))));
        for (int i = 0; i < character.getTalents().getCombat2().getAttributes().getLabels().size(); i++) {
            String label = character.getTalents().getCombat2().getAttributes().getLabels().get(i);
            String labelCustom = label.substring(0, label.indexOf("|"));
            String param = label.substring(label.indexOf("|"));

            //mỗi label có 15 lv <=> 1 mảng 15 phần tử
            ArrayList<String> arrayLv = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                String lv = getCombat2(character, param, j);
                arrayLv.add(lv);
            }
            arrayAttribute.add(new LabelAndLever(labelCustom, arrayLv));
            adapterAttribute.notifyDataSetChanged();
        }
    }

    private String getCombat2(Character character, String string, int index) {
        return getParamFromAttribute(character.getTalents().getCombat2().getAttributes(), string, index);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getAttributeCombat3(Character character, ArrayList<LabelAndLever> arrayAttribute, AttributeCombatAdapter adapterAttribute) {
        // lấy các label ra tạo thành item
        arrayAttribute.add(new LabelAndLever("", new ArrayList<>(Arrays.asList(sParam))));
        for (int i = 0; i < character.getTalents().getCombat3().getAttributes().getLabels().size(); i++) {
            String label = character.getTalents().getCombat3().getAttributes().getLabels().get(i);
            String labelCustom = label.substring(0, label.indexOf("|"));
            String param = label.substring(label.indexOf("|"));

            //mỗi label có 15 lv <=> 1 mảng 15 phần tử
            ArrayList<String> arrayLv = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                String lv = getCombat3(character, param, j);
                arrayLv.add(lv);
            }
            arrayAttribute.add(new LabelAndLever(labelCustom, arrayLv));
            adapterAttribute.notifyDataSetChanged();
        }
    }

    private String getCombat3(Character character, String string, int index) {
        return getParamFromAttribute(character.getTalents().getCombat3().getAttributes(), string, index);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getAttributeCombatSp(Character character, ArrayList<LabelAndLever> arrayAttribute, AttributeCombatAdapter adapterAttribute) {
        // lấy các label ra tạo thành item
        arrayAttribute.add(new LabelAndLever("", new ArrayList<>(Arrays.asList(sParam))));
        for (int i = 0; i < character.getTalents().getCombatsp().getAttributes().getLabels().size(); i++) {
            String label = character.getTalents().getCombatsp().getAttributes().getLabels().get(i);
            String labelCustom = label.substring(0, label.indexOf("|"));
            String param = label.substring(label.indexOf("|"));

            //mỗi label có 15 lv <=> 1 mảng 15 phần tử
            ArrayList<String> arrayLv = new ArrayList<>();
            String lv = getCombatSp(character, param);
            arrayLv.add(lv);
            arrayAttribute.add(new LabelAndLever(labelCustom, arrayLv));
            adapterAttribute.notifyDataSetChanged();
        }
    }

    private String getCombatSp(Character character, String string) {
        return getParamFromAttribute(character.getTalents().getCombatsp().getAttributes(), string, 0);
    }

    public String getParamFromAttribute(Attributes attributes, String s, int index) {
        //index là lv kỹ năng
        if (attributes.getParameters().getParam1() != null)
            s = s.replace("param1:", String.valueOf(attributes.getParameters().getParam1().get(index)));
        if (attributes.getParameters().getParam2() != null)
            s = s.replace("param2:", String.valueOf(attributes.getParameters().getParam2().get(index)));
        if (attributes.getParameters().getParam3() != null)
            s = s.replace("param3:", String.valueOf(attributes.getParameters().getParam3().get(index)));
        if (attributes.getParameters().getParam4() != null)
            s = s.replace("param4:", String.valueOf(attributes.getParameters().getParam4().get(index)));
        if (attributes.getParameters().getParam5() != null)
            s = s.replace("param5:", String.valueOf(attributes.getParameters().getParam5().get(index)));
        if (attributes.getParameters().getParam6() != null)
            s = s.replace("param6:", String.valueOf(attributes.getParameters().getParam6().get(index)));
        if (attributes.getParameters().getParam7() != null)
            s = s.replace("param7:", String.valueOf(attributes.getParameters().getParam7().get(index)));
        if (attributes.getParameters().getParam8() != null)
            s = s.replace("param8:", String.valueOf(attributes.getParameters().getParam8().get(index)));
        if (attributes.getParameters().getParam9() != null)
            s = s.replace("param9:", String.valueOf(attributes.getParameters().getParam9().get(index)));
        if (attributes.getParameters().getParam10() != null)
            s = s.replace("param10:", String.valueOf(attributes.getParameters().getParam10().get(index)));
        if (attributes.getParameters().getParam11() != null)
            s = s.replace("param11:", String.valueOf(attributes.getParameters().getParam11().get(index)));
        if (attributes.getParameters().getParam12() != null)
            s = s.replace("param12:", String.valueOf(attributes.getParameters().getParam12().get(index)));
        if (attributes.getParameters().getParam13() != null)
            s = s.replace("param13:", String.valueOf(attributes.getParameters().getParam13().get(index)));
        if (attributes.getParameters().getParam14() != null)
            s = s.replace("param14:", String.valueOf(attributes.getParameters().getParam14().get(index)));
        if (attributes.getParameters().getParam15() != null)
            s = s.replace("param15:", String.valueOf(attributes.getParameters().getParam15().get(index)));
        if (attributes.getParameters().getParam16() != null)
            s = s.replace("param16:", String.valueOf(attributes.getParameters().getParam16().get(index)));
        if (attributes.getParameters().getParam17() != null)
            s = s.replace("param17:", String.valueOf(attributes.getParameters().getParam17().get(index)));
        if (attributes.getParameters().getParam18() != null)
            s = s.replace("param18:", String.valueOf(attributes.getParameters().getParam18().get(index)));
        if (attributes.getParameters().getParam19() != null)
            s = s.replace("param19:", String.valueOf(attributes.getParameters().getParam19().get(index)));
        if (attributes.getParameters().getParam20() != null)
            s = s.replace("param20:", String.valueOf(attributes.getParameters().getParam20().get(index)));
        if (attributes.getParameters().getParam21() != null)
            s = s.replace("param21:", String.valueOf(attributes.getParameters().getParam21().get(index)));
        if (attributes.getParameters().getParam22() != null)
            s = s.replace("param22:", String.valueOf(attributes.getParameters().getParam22().get(index)));
        if (attributes.getParameters().getParam23() != null)
            s = s.replace("param23:", String.valueOf(attributes.getParameters().getParam23().get(index)));
        if (attributes.getParameters().getParam24() != null)
            s = s.replace("param24:", String.valueOf(attributes.getParameters().getParam24().get(index)));
        if (attributes.getParameters().getParam25() != null)
            s = s.replace("param25:", String.valueOf(attributes.getParameters().getParam25().get(index)));
        s = s.replace("|", "");
        s = customAttribute(s);
        return s;
    }

    private String customAttribute(String s) {
        //F1P
        do {
            if (s.contains("F1P}")){
                String custom = s.substring(s.indexOf("{")+1, s.indexOf("F1P}"));
                s = replaceOne(s, custom, convert(custom, 100));
                s = replaceOne(s, "F1P}", "%");
                s = replaceOne(s, "{", "");
            }
        } while (!replaceOne(s, "F1P}", "").equals("null"));
        //F1
        do {
            if (s.contains("F1}")){
                String custom = s.substring(s.indexOf("{")+1, s.indexOf("F1}"));
                s = s.replace(custom, convert(custom, 1));
                s = replaceOne(s, "F1}", "");
                s = replaceOne(s, "{", "");
            }
        } while (!replaceOne(s, "F1}", "").equals("null"));
        //F2P
        do {
            if (s.contains("F2P}")){
                String custom = s.substring(s.indexOf("{")+1, s.indexOf("F2P}"));
                s = s.replace(custom, convert(custom, 100));
                s = replaceOne(s, "F2P}", "%");
                s = replaceOne(s, "{", "");
            }
        } while (!replaceOne(s, "F2P}", "").equals("null"));
        //P
        do {
            if (s.contains("P}")){
                String custom = s.substring(s.indexOf("{")+1, s.indexOf("P}"));
                s = s.replace(custom, convert(custom, 100));
                s = replaceOne(s, "P}", "%");
                s = replaceOne(s, "{", "");
            }
        } while (!replaceOne(s, "P}", "").equals("null"));
        //I
        do {
            if (s.contains("I}")){
                String custom = s.substring(s.indexOf("{")+1, s.indexOf("I}"));
                s = s.replace(custom, convert(custom, 1));
                s = replaceOne(s, "I}", "");
                s = replaceOne(s, "{", "");
            }
        } while (!replaceOne(s, "I}", "").equals("null"));
        do {
            if (s.contains("F2")){
                String custom = s.substring(s.indexOf("{")+1, s.indexOf("F2}"));
                s = s.replace(custom, convert(custom, 100));
                s = replaceOne(s, "F2}", "");
                s = replaceOne(s, "{", "");
            }
        } while (!replaceOne(s, "F2}", "").equals("null"));
        return s;
    }

    private String convert(String s, int percent){
        double x = (double) Math.round((Double.parseDouble(s)*percent) * 10) / 10;
        if (percent == 1)
            return String.valueOf((int) x);
        return String.valueOf(x);
    }

    private String replaceOne(String s, String replace, String to){
        if (s.contains(replace))
            return s.substring(0,s.indexOf(replace))+ to +s.substring(s.indexOf(replace) + replace.length());
        return "null";
    }
}
