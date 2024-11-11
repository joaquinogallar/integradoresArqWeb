package Repository;

import Entitys.Pausa;
import Entitys.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface viajeRepository extends JpaRepository<Viaje,Long> {
@Query("SELECT v FROM Viaje v WHERE v.id_monopatin= :id_monopatin")
    List<Viaje>FindViajesPorId_monopatin(Long id_monopatin);

    @Query("SELECT v.id_monopatin FROM Viaje v WHERE FUNCTION('YEAR', v.fecha_inicio) = :anio GROUP BY v.id_monopatin HAVING COUNT(v) > :viajes")
    List<Long> findMonopatinesByViaje(int anio,int viajes);

    @Query("SELECT v.pausas FROM Viaje v WHERE v.id = :idViaje")
    List<Pausa> findPausasByIdViaje(Long idViaje);
}

