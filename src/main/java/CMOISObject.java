class CMOISObject {

    private String cmsp;
    //private String cmioNIF;
    //private float cmioValue;
    //private String userIBAN;
    private String userNIF;
    private Boolean userAuthorized;
    private float userValue;

    // Getter Methods
    String getCMSP() {return cmsp;}
    //String getCmioNIF() {return cmioNIF;}
    //float getCmioValue() {return cmioValue;}

    //String getUserIBAN() {return userIBAN;}
    String getUserNIF() {return userNIF;}
    Boolean getUserAuthorized() {return userAuthorized;}
    float getUserValue() {return userValue;}

    // Setter Methods

    void setCMSP(String cmioIBAN) {this.cmsp = cmsp;}
    //void setCmioNIF(String cmioNIF) {this.cmioNIF = cmioNIF;}
    //void setCmioValue(float cmioValue) {this.cmioValue = cmioValue;}

    //void setUserIBAN(String userIBAN) {this.userIBAN = userIBAN;}
    void setUserNIF(String userNIF) {this.userNIF = userNIF;}
    void setUserAuthorized(Boolean userAuthorized) {this.userAuthorized = userAuthorized;}
    void setUserValue(float userValue) {this.userValue = userValue;}

    CMOISObject(){

        cmsp = "";
        //cmioNIF = "";
        //cmioValue = 0;

        //userIBAN = "";
        userNIF = "";
        userAuthorized = false;
        userValue = 0;

    }

}