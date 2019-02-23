package web.models;

import web.models.views.LoginTable;

import java.util.ArrayList;
import java.util.List;

public class UploadModel {

    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean showAlert;

    public boolean getShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }
}
