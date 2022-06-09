package dds.grupo4.tpimpacto.services.apiSwagger;

import dds.grupo4.tpimpacto.services.apiSwagger.entities.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface ApiSwaggerService {


    @GET("paises")
    Call<ListadoPaises> paises(@Query("offset") int offset);


    @GET("provincias")
    Call<ListadoProvincias> provincias(@Query("offset") int offset,@Query("paisId") int paisId);


    @GET("municipios")
    Call<ListadoMunicipios> municipios(@Query("offset") int offset,@Query("provinciaId") int provinciaId);


    @GET("localidades")
    Call<ListadoLocalidades> localidades(@Query("offset") int offset,@Query("municipioId") int provinciaId);
    

    @GET("distancia")
    Call<Distancia> distancia(@Query("localidadOrigenId") int localidadorigenId,@Query("calleOrigen") String calleOrigen,@Query("alturaOrigen") int alturaOrigen,@Query("localidadDestinoId") int localidaddestinoid,@Query("calleDestino") String calleDestino,@Query("alturaDestino") String alturaDestino);





}
