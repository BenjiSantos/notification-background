package supervision.qw.gob.pe.testing.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class Emergencie {
    @SerializedName("GetEmergenciesLastestResult")
    @Expose
    private List<EmergencieObject> emergencieTotal;

    public List<EmergencieObject> getEmergenciesTotal() {
        return emergencieTotal;
    }
}
