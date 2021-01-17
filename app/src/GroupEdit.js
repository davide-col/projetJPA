import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class GroupEdit extends Component {

  emptyItem = {
    name: '',
    streetNumber: 0,
    streetName: '',
    city: '',
    clientName: '',
    email: ''
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const group = await (await fetch(`/api/appartment/${this.props.match.params.id}`)).json();
      this.setState({item: group});
    }
  }

  handleChange(client) {
    const target = client.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(client) {
    client.preventDefault();
    const {item} = this.state;

    await fetch('/api/appartment' + (item.id ? '/' + item.id : ''), {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/appartments');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Appartment' : 'Add Appartment'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Appartment Name</Label>
            <Input type="text" name="name" id="name" value={item.name || ''}
                   onChange={this.handleChange} autoComplete="name"/>
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-5 mb-3">
              <Label for="country">Street Number</Label>
              <Input type="number" name="streetNumber" id="streetNumber" value={item.streetNumber || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="country">Street Name</Label>
              <Input type="text" name="streetName" id="streetName" value={item.streetName || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>
          </div>
          <FormGroup>
              <Label for="city">City</Label>
              <Input type="text" name="city" id="city" value={item.city || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
              </FormGroup>
          <div className="row">
            <FormGroup className="col-md-5 mb-3">
              <Label for="clientName">Owner Name</Label>
              <Input type="text" name="clientName" id="clientName" value={item.clientName || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="email">Owner Email</Label>
              <Input type="text" name="email" id="email" value={item.email || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>
          </div>
            <FormGroup>
              <Button color="primary" type="submit">Save</Button>{' '}
              <Button color="secondary" tag={Link} to="/appartments">Cancel</Button>
            </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(GroupEdit);