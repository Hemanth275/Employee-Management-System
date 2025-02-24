import React,{useEffect, useState} from 'react'
import { deleteEmployee, listEmployees } from '../service/EmployeeService'
import { useNavigate } from 'react-router-dom'

const ListEmployeeComponent = () => {
    
    const [employees,setEmployee] = useState([])

    const navigator = useNavigate();
    
    useEffect(()=>{
       getEmployees();
    },[])

    function getEmployees(){
        listEmployees().then((response) => {
            setEmployee(response.data);
        }).catch(error => {
            console.error(error);
        })
    }
    function addNewEmployee(){
        navigator('/add-employee');
    }

    function updateEmployee(id){
        navigator(`/update-employee/${id}`)
    }

    function removeEmployee(id){
        deleteEmployee(id).then(res=>{
            getEmployees();
        }).catch(err =>{
            console.log(err);
        })
    }

    
    return (
    <div className='container'>
        <h2 className='text-center'>List of Employees</h2>
        <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
        <table className='table table-stripped table-bordered'>
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    employees.map(emp=>
                        <tr key = {emp.id}>
                            <td>{emp.id}</td>
                            <td>{emp.firstName}</td>
                            <td>{emp.lastName}</td>
                            <td>{emp.email}</td>
                            <td>
                                <button className='btn btn-info' onClick={()=> updateEmployee(emp.id)}>Update</button>
                                <button className='btn btn-danger' onClick={()=> removeEmployee(emp.id)} style={{marginLeft:'10px'}}>Delete</button>
                            </td>
                        </tr>)
                }
            </tbody>
        </table>

    </div>
  )
}

export default ListEmployeeComponent