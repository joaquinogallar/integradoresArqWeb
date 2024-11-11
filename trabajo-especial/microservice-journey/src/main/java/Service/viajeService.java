package Service;

import DTOS.viajeDTO;
import Entitys.Viaje;
import FeignClients.MonopatinFeignClient;
import FeignClients.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.viajeRepository ;

import java.time.LocalDate;
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
}
