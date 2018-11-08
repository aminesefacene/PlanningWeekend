import React from 'react';
import { connect } from "react-redux";
import { getId, connectUser, getUserActivities, getUserRegions, getAllActivities, getAllRegions } from '../actions/actions'
import { User } from '../user'
import { ConnectedActivityList } from './activityList'
import { ConnectedRegionList } from './regionList'
const axios = require('axios');

const mapDispatchToProps = dispatch => {
  return {
    getId: id => dispatch(getId(id)),
    connectUser: user => dispatch(connectUser(user)),
    getUserActivities: activities => dispatch(getUserActivities(activities)),
    getUserRegions: regions => dispatch(getUserRegions(regions)),
    getAllActivities: allActivities => dispatch(getAllActivities(allActivities)),
    getAllRegions: allRegions => dispatch(getAllRegions(allRegions))
  };
};

const mapStateToProps = (state) => {
  return {
    id: state.id,
    user: state.user
  }
}

class ConnectionForm extends React.Component {
    constructor(props) {
      super(props);
      this.state = {login: '', password: '', newLogin: '', newPassword:'', mailAddress: '' };
    }

    handleChangeLogin(event) {
        this.setState({login: event.target.value});
    }

    handleChangePassword(event) {
        this.setState({password: event.target.value});
    }

    handleChangeNewLogin(event) {
      this.setState({newLogin: event.target.value});
  }

  handleChangeNewPassword(event) {
      this.setState({newPassword: event.target.value});
  }

  handleChangeMailAddress(event) {
    this.setState({mailAddress: event.target.value});
  }

  resetAllInput(){
    this.setState({ login : '', password : '', newLogin : '', newPassword : '', mailAddress : '' });
  }

    getAllLists = () => {
      let url = 'http://localhost:8080/user/getUser/'+this.props.id;
      axios.get(url).then(response => this.props.getUserActivities(response.data.activities));
      axios.get(url).then(response => this.props.getUserRegions(response.data.regions));
      let urlAllActivities = 'http://localhost:8080/activity/getAll';
      axios.get(urlAllActivities).then(response => this.props.getAllActivities(response.data));
      let urlAllRegions = 'http://localhost:8080/region/getAll';
      axios.get(urlAllRegions).then(response => this.props.getAllRegions(response.data));
    }

    waitConnectionUser(data) {//fonction permettant d'attendre le résultat de la promesse avant de changer l'affichage
      this.props.getId(data);
      if(this.props.id===-1){
        this.props.connectUser(undefined);
      }else{
        let user = new User(this.state.login, this.state.password);
        this.getAllLists();
        this.props.connectUser(user);
      }
    }

    connectionUser = () => {
      if(this.state.login==='' || this.state.login===''){
        //ne fait rien si l'utilisateur ne saisi pas de login ou de mot de passe
      }else{
        let url = 'http://localhost:8080/user/getUserByLogin/'+this.state.login+'/'+this.state.password;
        axios.get(url).then(response => this.waitConnectionUser(response.data));
      }
    }

    createUser = () => {
      let newUser = { "username": this.state.newLogin,
                      "password": this.state.newPassword,
                      "mail": this.state.mailAddress,
                      "roles": null,//a verifier...
                      "activities": [],
                      "regions": []
                    }
      
      axios.post('http://localhost:8080/user/create', newUser).then(res => alert("Your account has been created !"));
      this.resetAllInput();
    }

    deconnectionUser = () => {
      this.resetAllInput();
      this.props.connectUser(undefined);
    }
  
    render() {
      if(this.props.user!==undefined){
        return <div><p>Bienvenue, {this.state.login}</p>
        <button onClick={this.deconnectionUser.bind(this)}>logout</button>
        <br/><br/>
        <ConnectedActivityList/>
        <br/><br/>
        <ConnectedRegionList/>
        </div>
      }else{
        return (
          <div>
          <h1>Connexion</h1>
          <label>
            <input type="text" value={this.state.login} placeholder="login" onChange={this.handleChangeLogin.bind(this)} />
          </label>
          <label>
            <input type="password" value={this.state.password} placeholder="password" onChange={this.handleChangePassword.bind(this)} />
          </label>
          <button onClick={this.connectionUser.bind(this)}>login</button>
          <h1>Registration</h1>
          <label>
            <input type="text" value={this.state.newLogin} placeholder="login" onChange={this.handleChangeNewLogin.bind(this)} />
          </label>
          <label>
            <input type="password" value={this.state.newPassword} placeholder="password" onChange={this.handleChangeNewPassword.bind(this)} />
          </label>
          <label>
            <input type="test" value={this.state.mailAddress} placeholder="mail address" onChange={this.handleChangeMailAddress.bind(this)} />
          </label>
          <button onClick={this.createUser.bind(this)}>register</button>
          </div>
        );
      }
    }
}

const ConnectedConnectionForm = connect(mapStateToProps, mapDispatchToProps)(ConnectionForm);

export {
  ConnectedConnectionForm
}
