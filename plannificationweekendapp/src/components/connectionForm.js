import React from 'react';
import { connect } from "react-redux";
import { connectUser, getUserActivities, getUserRegions, getAllActivities, getAllRegions } from '../actions/actions'
import { User } from '../user'
import { ConnectedActivityList } from './activityList'
import { ConnectedRegionList } from './regionList'
const axios = require('axios');

const mapDispatchToProps = dispatch => {
  return {
    connectUser: user => dispatch(connectUser(user)),
    getUserActivities: activities => dispatch(getUserActivities(activities)),
    getUserRegions: regions => dispatch(getUserRegions(regions)),
    getAllActivities: allActivities => dispatch(getAllActivities(allActivities)),
    getAllRegions: allRegions => dispatch(getAllRegions(allRegions))
  };
};

const mapStateToProps = (state) => {
  return {
    user: state.user
  }
}

class ConnectionForm extends React.Component {
    constructor(props) {
      super(props);
      this.state = { login: '', password: '' };
    }

    handleChangeLogin(event) {
        this.setState({login: event.target.value});
    }

    handleChangePassword(event) {
        this.setState({password: event.target.value});
    }

    getAllLists = () => {
      let url = 'http://localhost:8080/user/getUser/5';//CHANGER l'ID EN FONCTION DU USER QUAND LE BACKEND SERA OK
      axios.get(url).then(response => this.props.getUserActivities(response.data.activities));
      axios.get(url).then(response => this.props.getUserRegions(response.data.regions));
      let urlAllActivities = 'http://localhost:8080/activity/getAll';
      axios.get(urlAllActivities).then(response => this.props.getAllActivities(response.data));
      let urlAllRegions = 'http://localhost:8080/region/getAll';
      axios.get(urlAllRegions).then(response => this.props.getAllRegions(response.data));
    }

    connectionUser = () => {
        let user = new User(this.state.login, this.state.password);
        this.getAllLists();
        this.props.connectUser(user);
    }

    deconnectionUser = () => {
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
          <label>
            <input type="text" placeholder="login" onChange={this.handleChangeLogin.bind(this)} />
          </label>
          <label>
            <input type="password" placeholder="password" onChange={this.handleChangePassword.bind(this)} />
          </label>
          <button onClick={this.connectionUser.bind(this)}>login</button></div>
        );
      }
    }
}

const ConnectedConnectionForm = connect(mapStateToProps, mapDispatchToProps)(ConnectionForm);

export {
  ConnectedConnectionForm
}
