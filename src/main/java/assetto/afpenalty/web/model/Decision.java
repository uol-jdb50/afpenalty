package assetto.afpenalty.web.model;

import java.util.UUID;

public class Decision {
    private String decisionID;
    private String text;
    private boolean variable;

    public Decision() {

    }
    public Decision(String _decisionID, String _text, boolean _variable) {
        decisionID = _decisionID;
        text = _text;
        variable = _variable;
    }
    public String getDecisionID() {
        return decisionID;
    }
    public String getText() {
        return text;
    }
    public boolean getVariable() {
        return variable;
    }
    public void setDecisionID(String _decisionID) {
        decisionID = _decisionID;
    }
    public void setDecisionID() {
        decisionID = UUID.randomUUID().toString();
    }
    public void setText(String _text) {
        text = _text;
    }
    public void setVariable(boolean _variable) {
        variable = _variable;
    }
}
