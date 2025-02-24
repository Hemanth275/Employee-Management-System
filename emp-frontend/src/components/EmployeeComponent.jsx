import React, { useEffect, useState } from 'react'
import { createEmployee, getEmployee, updateEmployee } from '../service/EmployeeService'

import { useNavigate, useParams } from 'react-router-dom'

const EmployeeComponent = () => {

    const [firstName,setFirstName] = useState('')
    const [lastName,setLastName] = useState('')
    const [email,setEmail] = useState('')

    const {id} = useParams();

    const [errors,setErrors]=useState({
        firstName:'',
        lastName:'',
        email:''

    })
    
    useEffect(() => {
        if(id){
            getEmployee(id).then(res => {
                setFirstName(res.data.firstName);
                setLastName(res.data.lastName);
                setEmail(res.data.email);
            }).catch(err =>{
                console.error(err);
            })
        }
    },[id]);

    const navigator = useNavigate()

    function saveorUpdateEmployee(e){
        e.preventDefault();

        if(validateForm()){
            const employee = {firstName,lastName,email}
            console.log(employee);
            if(id){
                updateEmployee(id,employee).then(res => {
                    console.log(res.data);
                    navigator('/employees');
                }).catch(err => {
                    console.error(err);
                })
            }else{
                createEmployee(employee).then((res)=>{
                    console.log(res.data);
                    navigator('/employees')
                }).catch(err => {
                    console.log(err);
                })
            }
           
        
        }

        
    }

    function validateForm(){
        let valid = true;
        const errorsCopy = {... errors}

        if(firstName.trim()){
            errorsCopy.firstName ='';
        }else{
            errorsCopy.firstName ='FirstName is Required';
            valid = false;
        }

        if(lastName.trim()){
            errorsCopy.lastName='';
        }else{
            errorsCopy.lastName = 'LastName is Required';
            valid = false;
        }

        if(email.trim()){
            errorsCopy.email='';
        }else{
            errorsCopy.email= 'Email required';
            valid = false;
        }

        setErrors(errorsCopy);

        return valid;
    }

    function pageTitle(){
        if(id){
            return <h2 className='text-center'>Update Employee</h2>
        }else{
            return <h2 className='text-center'>Add Employee</h2>
        }
    }

   return (
    <div className='container'>
        <br></br>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3' >
                    {
                        pageTitle()
                    }
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                            <label className='form-label'>First Name</label>
                            <input
                                type='text'
                                placeholder='Enter employee First Name'
                                name = 'firstName'
                                value={firstName}
                                className={`form-control ${ errors.firstName? 'is-invalid':''}`}
                                onChange = {(e)=>setFirstName(e.target.value)}
                            >
                            </input>
                            { errors.firstName && <div className='invalid-feedback'>{ errors.firstName}</div>}
                            </div>
                            <div className='form-group mb-2'>
                            <label className='form-label'>Last Name</label>
                            <input
                                type='text'
                                placeholder='Enter employee Last Name'
                                name = 'lastName'
                                value={lastName}
                                className={`form-control ${ errors.lastName? 'is-invalid':''}`}
                                onChange = {(e)=>setLastName(e.target.value)}
                            >
                            </input>
                            { errors.lastName && <div className='invalid-feedback'>{ errors.lastName}</div>}
                            </div>
                            <div className='form-group mb-2'>
                            <label className='form-label'>Email</label>
                            <input
                                type='email'
                                placeholder='Enter employee Email'
                                name = 'email'
                                value={email}
                                className={`form-control ${ errors.lastName? 'is-invalid':''}`}
                                onChange = {(e)=>setEmail(e.target.value)}
                            >
                            </input>
                            { errors.email && <div className='invalid-feedback'>{ errors.email}</div>}
                            </div>
                            <button className='btn btn-success' onClick={saveorUpdateEmployee}>Submit</button>
                        </form>

                    </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent