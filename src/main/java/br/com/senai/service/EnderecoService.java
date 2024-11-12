package br.com.senai.service;


import br.com.senai.entity.EnderecoEntity;
import br.com.senai.exception.EntidadeException;
import br.com.senai.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

//    public List<EnderecoEntity> findAll(){
//        return enderecoRepository.findAll();
//    }
//
//    public EnderecoEntity findById(Long id){
//        return enderecoRepository.findById(id).orElseThrow(() ->new EntidadeException("Endereço não encontrado"));
//    }

    public EnderecoEntity cadastrarEndereco(EnderecoEntity endereco){
        return enderecoRepository.save(endereco);
    }

    public List<EnderecoEntity> listarEnderecos(){
        return enderecoRepository.findAll();
    }

    public EnderecoEntity editarEndereco(Long idEndereco, EnderecoEntity endereco){
        EnderecoEntity enderecoOld = enderecoRepository.findById(idEndereco).orElseThrow(() -> new EntidadeException("Endreço não encontrado!!"));
        enderecoOld.setUf(endereco.getUf());
        enderecoOld.setCep(endereco.getCep());
        enderecoOld.setBairro(endereco.getBairro());
        enderecoOld.setEstado(endereco.getEstado());
        enderecoOld.setRua(endereco.getRua());
        endereco.setCidade(endereco.getCidade());

        return enderecoRepository.save(enderecoOld);
    }


    public EnderecoEntity buscarEnderecoPorId(Long idEndereco){
        EnderecoEntity endereco = enderecoRepository.findById(idEndereco).orElseThrow(() -> new EntidadeException("Cadastro de Endereco não encontrado!!"));
        return endereco;
    }

    public EnderecoEntity buscarEnderecoPorCep(String cep){
        return enderecoRepository.buscarEnderecoPorCep(cep).orElseThrow(() -> new EntidadeException("Endereço não encontrado"));

    }

    public List<EnderecoEntity> buscarEnderecoPorCidade(String cidade){
        return enderecoRepository.buscarEnderecoPorCidade(cidade);
    }

    public List<EnderecoEntity> buscarEnderecoPorUF(String uf){
        return enderecoRepository.buscarEnderecoPorUF(uf);
    }

    public void excluirEndereco(Long idEndereco){
        enderecoRepository.deleteById(idEndereco);
    }
}
