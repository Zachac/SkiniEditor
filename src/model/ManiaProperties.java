package model;

import java.util.Map;

@SuppressWarnings("unused") // TODO
public class ManiaProperties {
    
    private ManiaKeyCount keys;
    private double columnStart;
    private double columnRight;
    private Map<Integer, MColor> collumnColors;
    private Map<Integer, MColor> collumnLight;
    private MColor colourHold;
    private MColor colourBreak;
    private MColor colourColumnLine;
    private MColor colourBarline;
    private MColor colourJudgementLine;
    private MColor colourKeyWarning;
    private Map<Integer, Double> columnLineWidth;
    private ManiaComboBurstStyle comboBurstStyle;
    private double barlineHeight;
    private Map<Integer, Double> columnWidth;
    private double widthForNoteHeightScale;
    private Map<Integer, Double> columnSpacing;
    private Map<Integer, Double> lightingNWidth;
    private Map<Integer, Double> lightingLWidth;
    private ManiaSpecialStyle specialStyle;
    private boolean judgementLine;
    private int hitPosition;
    private int comboPosition;
    private int scorePosition;
    private int lightPosition;
    private boolean upsideDown;
    private boolean splitStages;
    private boolean separateScore;
    private double stageSeparation;
    private boolean keysUnderNotes;
    private int lightFramePerSecond;
    private ManiaNoteBodyStyle noteBodyStyle;
    private Map<Integer, ManiaNoteBodyStyle> noteBodyStyleInteger;

    private boolean keyFlipWhenUpsideDown;
    private Map<Integer, Boolean> keyFlipWhenUpsideDownInteger;
    private boolean NoteFlipWhenUpsideDown;
    private Map<Integer, Boolean> NoteFlipWhenUpsideDownInteger;
    private boolean NoteFlipWhenUpsideDownH;
    private Map<Integer, Boolean> NoteFlipWhenUpsideDownHInteger;
    private boolean NoteFlipWhenUpsideDownL;
    private Map<Integer, Boolean> NoteFlipWhenUpsideDownLInteger;
    private boolean NoteFlipWhenUpsideDownT;
    private Map<Integer, Boolean> NoteFlipWhenUpsideDownTInteger;

    private Map<Integer, String> keyImage;
    private Map<Integer, String> keyImageD;
    private Map<Integer, String> NoteImage;
    private Map<Integer, String> NoteImageH;
    private Map<Integer, String> NoteImageL;
    private Map<Integer, String> NoteImageT;

    private String stageRight;
    private String StageLeft;
    private String StageBottom;
    private String StageHint;
    private String StageLight;
    private String Hit0;
    private String Hit50;
    private String Hit100;
    private String Hit200;
    private String Hit300;
    private String Hit300g;
    private String LightingN;
    private String LightingL;
    private String WarningArrow;

    public ManiaProperties(ManiaKeyCount keys) {
        throw new UnsupportedOperationException("Cannot instantiate mania properties. Not yet implemented."); //TODO
    }
}
