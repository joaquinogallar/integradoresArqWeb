package Service;

import DTOS.viajeDTO;
import Entitys.Pausa;
import Entitys.Viaje;
import FeignClients.MonopatinFeignClient;
import FeignClients.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.viajeRepository ;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class viajeService {
    @Autowired
    viajeRepository viajeRepository;
    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;
    @Autowired
    PausaService pausaService;
    public List<viajeDTO> getAll() {
        try{
            List<Viaje> viajes  =   this.viajeRepository.findAll() ;
            List<viajeDTO> viajesDTO = new ArrayList<viajeDTO>();
            for (Viaje v : viajes) {
                viajesDTO.add(new viajeDTO(v)) ;
            }
            return viajesDTO;
        }catch (Exception e){
        throw new RuntimeException(e);
        }
    }
    public void createViaje(Long monopatinId, Long usuarioId){
        if (monopatinFeignClient.getMonopatinById(monopatinId) != null && userFeignClient.getUsuarioById(usuarioId)!=null) {
            Viaje viaje = new Viaje(LocalDate.now(), monopatinId, usuarioId);
            viajeRepository.save(viaje);
        }
    }

    public List<viajeDTO> getViajeByMonopatin(Long idMonopatin) {
        List<Viaje> viajes= viajeRepository.FindViajesPorId_monopatin(idMonopatin);
        List<viajeDTO> aux = new ArrayList<>();
        for (Viaje v : viajes) {
            aux.add(new viajeDTO(v)) ;
        }
        return aux;

    }
    public List<Long>getMonopatinesByViajes(int cantidad, int anio){
        return viajeRepository.findMonopatinesByViaje(anio,cantidad);
    }

    /*public viajeDTO endViaje(Long idViaje){
        Viaje v  = viajeRepository.findById(idViaje).orElse(null);

        if(v!=null){
            v.setFecha_fin(LocalDate.now());
            //SETEAR KILOMETROS RECORRIDOS ?
            viajeRepository.save(v);
            List<Pausa>pausas = viajeRepository.findPausasByIdViaje(idViaje);
            double TarifaXpausas = 0 ;
            for (Pausa pausa : pausas) {
                Long minutos = ChronoUnit.MINUTES.between(pausa.getHora_inicio(), pausa.getHora_fin());
                if(minutos>15) {
                    Long minutosFinal = minutos-15 ;
                    minutosFinal = minutosFinal*

                }
            }

        */


}
