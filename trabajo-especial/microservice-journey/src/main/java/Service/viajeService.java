package Service;

import DTOS.viajeDTO;
import Entitys.Pausa;
import Entitys.Tarifa;
import Entitys.Viaje;
import FeignClients.MonopatinFeignClient;
import FeignClients.ParadaFeignClient;
import FeignClients.UserFeignClient;
import Model.MonopatinDTO;
import Model.UserDTO;
import Repository.TarifaRepository;


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
            MonopatinDTO m = monopatinFeignClient.getMonopatinById(monopatinId);
            Viaje viaje = new Viaje(LocalDate.now(),usuarioId,monopatinFeignClient.getMonopatinById(monopatinId),m.getX(),m.getY());
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
        double xfinal = 0 ;
        double yfinal = 0;
        Viaje v  = viajeRepository.findById(idViaje).orElse(null);
        if(v!=null){

            MonopatinDTO m = v.getMonopatin();
          if(v.getMonopatin()!=null){
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
                UserDTO u  =  userFeignClient.getUsuarioById(v.getId_usuario()).getBody();
                u.setBalance(tarifaNormal+tarifaXpausas);
              return new viajeDTO(v);
                }
            }

       throw new RuntimeException();
    }
        }


