import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class AppartmentList extends Component {

  constructor(props) {
    super(props);
    this.state = {appartments: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/appartments')
      .then(response => response.json())
      .then(data => this.setState({appartments: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/appartment/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedAppartments = [...this.state.appartments].filter(i => i.id !== id);
      this.setState({appartments: updatedAppartments});
    });
  }

  render() {
    const {appartments, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const appartmentList = appartments.map(appartment => {
      return <tr key={appartment.id}>
        <td style={{whiteSpace: 'nowrap'}}>{appartment.name}</td>
        <td>{appartment.address.streetNumber}, {appartment.address.streetName}, {appartment.address.city}</td>
        <td>{appartment.client.name} : {appartment.client.email}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/appartments/" + appartment.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(appartment.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/appartments/new">Add Appartment</Button>
          </div>
          <h3>Appartment Manager 2021</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="20%">Location</th>
              <th>Owner</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {appartmentList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default AppartmentList;