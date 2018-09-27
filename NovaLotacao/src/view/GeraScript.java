package view;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class GeraScript {

	
	public void gerarScript(int lotacao, int modelo, String caminho) throws IOException {
		
		 FileWriter arq = new FileWriter(caminho+"\\Script"+lotacao+".sql");
		    PrintWriter gravarArq = new PrintWriter(arq);
		 
		    gravarArq.printf("-- BANCO TRF1 %n"+
	"BEGIN %n"+
	"    FOR rec IN (SELECT   lolo_nr_localizacao%n"+
	"                  FROM   gpd_tb_lolo_loc_lotacao%n"+
	"                 WHERE   lolo_cd_lotacao = %d) --código da lotação modelo%n"+
	"    LOOP%n"+
	"        BEGIN%n"+
	"            INSERT INTO gpd.gpd_tb_lolo_loc_lotacao (lolo_id_localizacao_lotacao, lolo_nr_localizacao, lolo_cd_secsubsec, lolo_cd_secsubsec_lotacao, lolo_cd_lotacao)%n"+
	"              VALUES   (gpd_sq_lolo.NEXTVAL,%n"+
	"                        rec.lolo_nr_localizacao,%n"+
	"                        NULL,%n"+
	"                        NULL,%n"+
	"                        %d);--codigo da nova lotação%n"+
	"        END;%n"+
	"    END LOOP;%n"+
	"END;%n"+
	"/%n"+
	"--inserir atividades%n"+
	"BEGIN%n"+
	"   FOR rec IN (SELECT lolo_id_localizacao_lotacao%n"+
	"                 FROM gpd.gpd_tb_lolo_loc_lotacao%n"+
	"                WHERE lolo_cd_lotacao = %2d) --codigo da nova lotação%n"+
	"   LOOP%n"+
	"      BEGIN%n"+
	"         INSERT INTO gpd.gpd_tb_lola_loc_lot_atividade%n"+
	"(SELECT gpd.gpd_sq_lola.NEXTVAL, rec.lolo_id_localizacao_lotacao,%n"+
	"                    lola_nr_atividade%n"+
	"               FROM gpd.gpd_tb_lola_loc_lot_atividade%n"+
	"              WHERE lola_id_localizacao_lotacao = 4817);%n"+
	"END;%n"+
	"END LOOP;%n"+
	"END;%n"+
	"/%n"+
	"COMMIT;%n"+
	"/%n", modelo, lotacao, lotacao
	);
		    String msg = String.format("\nO Script sql %d foi gravada com sucesso em %s\\Script%d.sql\".\n", lotacao,caminho, lotacao);
		 
		    arq.close();
		 
		    JOptionPane.showMessageDialog(null, msg);
		}
			
		

}

