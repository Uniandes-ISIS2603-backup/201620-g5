/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.g5.bibliotecas.api;

import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.List;

public interface IMultaLogic {
    public List<MultaEntity> getMultas();
    public MultaEntity getMulta(Long id);
    public List<MultaEntity> getMultasByUsuario(Long idUsuario);
    public List<MultaEntity> getMultasByRecurso(Long idUsuario, Long idRecurso);
    public List<MultaEntity> getMultasByBiblioteca(Long idUsuario, Long idBiblioteca);
    
    public MultaEntity createMulta(MultaEntity multa, Long idUsuario, Long idRecurso, Long idBiblioteca, int tRecurso) throws BibliotecaLogicException; 
    public MultaEntity updateMulta(MultaEntity multa) throws BibliotecaLogicException;
    public MultaEntity deleteMulta(Long id) throws BibliotecaLogicException;
}
