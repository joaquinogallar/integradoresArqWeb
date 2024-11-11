package Service;

import DTOS.viajeDTO;
import Entitys.Pausa;
import Entitys.Tarifa;
import Entitys.Viaje;
import FeignClients.MonopatinFeignClient;
import FeignClients.ParadaFeignClient;
import FeignClients.UserFeignClient;
import Repository.TarifaRepository;
import com.example.microservicioMonopatin.entity.Monopatin;
import com.example.microservicioMonopatin.entity.MonopatinDTO;
import com.example.microserviciouser.DTOS.userDTO;
import org.apache.catalina.User;
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
    ParadaFeignClient paradaFeignClient;
    @Autowired
    TarifaRepository tarifaRepository ;
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

    public viajeDTO endViaje(Long idViaje){
        int xfinal = 0 ;
        int yfinal = 0;
        Viaje v  = viajeRepository.findById(idViaje).orElse(null);
        if(v!=null){

          MonopatinDTO m = monopatinFeignClient.getMonopatinById(v.getId_monopatin()).getBody();
          if(m!=null){
                if(paradaFeignClient.getParadaByX(m.getX(),m.getY())!=null){
                    v.setFecha_fin(LocalDate.now());
                    m.setDisponible(true);
                    if(v.getXOrigen()- m.getX() <0)
                    xfinal = (v.getXOrigen()- m.getX())*-1  ;
                    else
                        xfinal = v.getXOrigen()- m.getX();
                    }
                if(v.getYOrigen()- m.getY() <0)
                    yfinal = (v.getYOrigen()- m.getY())*-1  ;
                else
                    yfinal = v.getYOrigen()- m.getY();

                v.setkmRecorridos(xfinal+yfinal);
                    viajeRepository.save(v);
                    List<Pausa>pausas = viajeRepository.findPausasByIdViaje(idViaje);
                    double tarifaXpausas = 0 ;
                    double tarifaNormal = 0;
                    Tarifa ta = tarifaRepository.getTarifaNormalEnPlazoValido();

                    tarifaNormal = (xfinal+yfinal)*ta.getTarifa();
                    for (Pausa pausa : pausas) {
                        Long minutos = ChronoUnit.MINUTES.between(pausa.getHora_inicio(), pausa.getHora_fin());
                        if(minutos>15) {
                            Tarifa tarifaEspecial = tarifaRepository.getTarifaExtraEnPlazoValido();
                            Long minutosFinal = minutos-15 ;
                             tarifaXpausas = minutosFinal*tarifaEspecial.getTarifaEspecial();
                }

            }
                userDTO u  = userFeignClient.getUsuarioById(v.getId_usuario()).getBody();
                u.setBalance(tarifaNormal+tarifaXpausas);
              return new viajeDTO(v);
                }
            }

       throw new RuntimeException();
    }
        }


