import React from 'react';
import { connect } from "react-redux";
import { addUserActivity } from '../actions/actions'
const axios = require('axios');

const mapDispatchToProps = dispatch => {
    return {
        addUserActivity: activity => dispatch(addUserActivity(activity))
    };
  };

const mapStateToProps = (state) => {
    return {
        id: state.id,
        user: state.user,
        mailAddress: state.mailAddress,
        userActivities: state.activityList,
        allActivities: state.availableActivities
    }
}

class ActivityList extends React.Component {

    constructor(props) {
        super(props);
        this.state = { selectedActivity: '', selectedLevel : '' };
    }

    handleChangeSelectedActivity(event) {
        this.setState({selectedActivity: event.target.value});
    }

    handleChangeSelectedLevel(event) {
        this.setState({selectedLevel: event.target.value});
    }

    displayUserActivities = () => {
        var Data = this.props.userActivities, MakeItem = function(X) {
              return <li key={X.name+"|"+X.level}>{X.name+"|"+X.level}</li>
        };
        return Data.map(MakeItem);
    }

    displayAllActivities = () => {
        var Data = this.props.allActivities, MakeItem = function(X) {
              return <option key={X.name}>{X.name}</option>
        };
        return Data.map(MakeItem);
    }



    addActivityToList = () => {
        for(let i=0;i<this.props.allActivities.length;i++){
            if(this.props.allActivities[i].name===this.state.selectedActivity && this.props.allActivities[i].level===this.state.selectedLevel){
                let boolContain=false;
                for(let j=0;j<this.props.userActivities.length;j++){
                    //vérifie si l'activité sélectionnée n'appartient pas a la liste de l'utilisateur
                    if(JSON.stringify(this.props.userActivities[j])===JSON.stringify(this.props.allActivities[i])){
                        alert("cette activité fait déjà partie de votre liste d'activitées");
                        boolContain=true;
                    }
                }
                if(!boolContain){
                    let newActivities = this.props.userActivities;
                    this.props.addUserActivity(this.props.allActivities[i])//update state...
                    newActivities.push(this.props.allActivities[i]);//update BDD...

                    let urlAddActivity = 'http://localhost:8080/user/update/'+this.props.id;
                    let newUser = { "username": this.props.user.login,
                      "password": this.props.user.password,
                      "mail": this.props.mailAddress,
                      "roles": null,//a verifier...
                      "activities": newActivities,
                      "regions": []
                    }
                    axios.put(urlAddActivity, newUser).then(res => console.log(newUser));
      
                }
            }
        }
    }

    render() {
        return <div>
            <h3>Liste des activitées</h3>
            <ul>{this.displayUserActivities()}</ul>
            <select onChange={this.handleChangeSelectedActivity.bind(this)} value={this.state.selectedActivity}><option></option>{this.displayAllActivities()}</select>
            <select onChange={this.handleChangeSelectedLevel.bind(this)} value={this.state.selectedLevel}><option></option><option>EASY</option><option>MEDIUM</option><option>HARD</option></select>
            <button onClick={this.addActivityToList.bind(this)}>+</button>
            </div>
    }
}

const ConnectedActivityList = connect(mapStateToProps, mapDispatchToProps)(ActivityList);

export {
    ConnectedActivityList
}