import React, { Component } from "react";
import EmployeeService from "../service/EmployeeService";

class EmployeeComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      employee: {},
      department: {},
      organization: {},
    };
  }

  componentDidMount() {
    EmployeeService.getEmployee().then((response) => {
      this.setState({ employee: response.data.employee });
      this.setState({ department: response.data.department });
      this.setState({ organization: response.data.organization });

      console.log(this.state.employee);
      console.log(this.state.department);
      console.log(this.state.organization);
    });
  }
  render() {
    return
     <div>
      <div className="container card offset-md-3">
          <h3 className="text-center card-header">View Employee Details</h3>
          <div className="card-body">
            <div className="=row">
              <p><strong>Employee First Name: </strong>{this.state.employee.firstName} </p>
            </div>
            </div>
              <div className="=row">
              <p><strong>Employee First Name: </strong>{this.state.employee.lastName} </p>
            </div>
              <div className="=row">
              <p><strong>Employee First Name: </strong>{this.state.employee.email} </p>
            </div>           
            </div>
          <div>
          </div>
    </div>
  }
}

export default EmployeeComponent;
