package br.edu.iftm.tspi.cadastro.resorce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.tspi.cadastro.dto.CadastroDTO;

@Controller

public class CadastroResource {

    private List<CadastroDTO> cadastros = new ArrayList<>();

    // public CadastroResource (){
    //     CadastroDTO cadastro   = new CadastroDTO();
    //     cadastro.setNickName("barbs_ramos");;
    //     cadastro.setLendaFavorita("Ash");
    //     cadastro.setPartidasJogadas(1000);
    //     cadastro.setPartidasGanhas(200);
    //     cadastro.setMaximoDano(5000);
    //     cadastros.add(cadastro); testes

    // }

    @PostMapping("cadastroPost")
    public String doPost(CadastroDTO dto, Model model){
        boolean nomeJaExiste = false;
        for (CadastroDTO cadastro : cadastros) {
            if (cadastro.getNickName().equalsIgnoreCase(dto.getNickName())) {
                cadastro.setLendaFavorita(dto.getLendaFavorita());
                cadastro.setPartidasJogadas(dto.getPartidasJogadas());
                cadastro.setPartidasGanhas(dto.getPartidasGanhas());
                cadastro.setMaximoDano(dto.getMaximoDano());
                nomeJaExiste = true;
                break;
            }
        }
        if (!nomeJaExiste) 
            cadastros.add(dto);
        
        return doGet(model);
        }
    
    @RequestMapping("cadastroPost")
    public String doGet(Model model){
        model.addAttribute("cadastros", cadastros);
        // nova linha
        model.addAttribute("cadastro", new CadastroDTO());
        // erro anterior
        // System.out.println(cadastros.get(0).getNickName());
        return "lista";
    }

    @RequestMapping("cadastroDelete")
    public String cadastroDelete(@RequestParam("nickName") String nickName, Model model){
        for (CadastroDTO cadastro : cadastros) {
            if(cadastro.getNickName().equals(nickName)){
                cadastros.remove(cadastro);
                break;
            }
        }
        return doGet(model);
    }

    @RequestMapping("/cadastroUpdate")
   public String cadastroUpdate(CadastroDTO dto, Model model) {
      CadastroDTO cadastroDTO = new CadastroDTO();

        for (CadastroDTO cadastro : cadastros) {
            if (cadastro.getNickName().toLowerCase().equals(dto.getNickName().toLowerCase())) {
               cadastroDTO = cadastro;
               break;
            }
        }
        model.addAttribute("cadastro", cadastroDTO);
        model.addAttribute("cadastros", cadastros);
        return "lista";
   }

}
