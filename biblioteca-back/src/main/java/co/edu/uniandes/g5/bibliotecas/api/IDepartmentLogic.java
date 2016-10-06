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

import co.edu.uniandes.csw.company.entities.DepartmentEntity;
import co.edu.uniandes.csw.company.entities.EmployeeEntity;
import java.util.List;

public interface IDepartmentLogic {
  
    public List<DepartmentEntity> getDepartments(Long companyId);
    public DepartmentEntity getDepartment(Long departmentid);
    public DepartmentEntity createDepartment(Long companyid, DepartmentEntity entity);
    public DepartmentEntity updateDepartment(Long companyid, DepartmentEntity entity);
    public void deleteDepartment(Long id);
    public List<EmployeeEntity> listEmployees(Long departmentId);
    public EmployeeEntity getEmployee(Long departmentId, Long employeesId);
    public EmployeeEntity addEmployee(Long departmentId, Long employeesId);
    public List<EmployeeEntity> replaceEmployees(Long departmentId, List<EmployeeEntity> list);
    public void removeEmployee(Long departmentId, Long employeesId);
}
