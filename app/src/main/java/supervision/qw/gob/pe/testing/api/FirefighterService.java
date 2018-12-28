package supervision.qw.gob.pe.testing.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import supervision.qw.gob.pe.testing.api.model.Emergencie;

public interface FirefighterService {

    @GET("GetEmergencies/")
    Call<List<Emergencie>> getEmergencies();

}

