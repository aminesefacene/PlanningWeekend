import React from 'react';
import { connect } from "react-redux";
import { addUserActivity, getUserActivities } from '../actions/actions'
const axios = require('axios');

const mapDispatchToProps = dispatch => {
    return {
        addUserActivity: activity => dispatch(addUserActivity(activity)),
        getUserActivities : activities => dispatch(getUserActivities(activities))
    };
  };

const mapStateToProps = (state) => {
    return {
        id: state.id,
        user: state.user,
        mailAddress: state.mailAddress,
        userActivities: state.activityList,
        allActivities: state.availableActivities,
        userRegions: state.regionList
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
              return <option key={X.name+"|"+X.level+"|key"}>{X.name}</option>
        };
        return Data.map(MakeItem);
    }

    removeActivityToList = () => {
        for(let i=0;i<this.props.allActivities.length;i++){
            if(this.props.allActivities[i].name===this.state.selectedActivity && this.props.allActivities[i].level===this.state.selectedLevel){
                let boolContain=false;
                let newActivities = [];//on construit la nouvelle liste d'activitées de l'utilisateur
                for(let j=0;j<this.props.userActivities.length;j++){
                    //vérifie si l'activité sélectionnée appartient à la liste de l'utilisateur
                    if(JSON.stringify(this.props.userActivities[j])===JSON.stringify(this.props.allActivities[i])){
                        boolContain=true;
                    }else{
                        newActivities.push(this.props.userActivities[j]);
                    }
                }
                if(boolContain){
                    this.props.getUserActivities(newActivities)

                    let urlRemoveActivity = 'http://localhost:8080/user/update/'+this.props.id;
                    let newUser = { "username": this.props.user.login,
                      "password": this.props.user.password,
                      "mail": this.props.mailAddress,
                      "roles": {"idRole":56,"role":"UTILISATEUR"},//a changer
                      "activities": newActivities,
                      "regions": this.props.userRegions
                    }
                    axios.put(urlRemoveActivity, newUser).then(res => console.log());
                }else{
                    alert("cette activité associée à ce niveau ne fait pas partie de votre liste d'activitées");
                }
            }
        }
    }

    addActivityToList = () => {
        for(let i=0;i<this.props.allActivities.length;i++){
            if(this.props.allActivities[i].name===this.state.selectedActivity && this.props.allActivities[i].level===this.state.selectedLevel){
                let boolContain=false;
                for(let j=0;j<this.props.userActivities.length;j++){
                    //vérifie si l'activité sélectionnée n'appartient pas à la liste de l'utilisateur
                    if(JSON.stringify(this.props.userActivities[j])===JSON.stringify(this.props.allActivities[i])){
                        alert("cette activité associée à ce niveau fait déjà partie de votre liste d'activitées");
                        boolContain=true;
                    }
                }
                if(!boolContain){
                    let newActivities = this.props.userActivities;
                    this.props.addUserActivity(this.props.allActivities[i])
                    newActivities.push(this.props.allActivities[i]);

                    let urlAddActivity = 'http://localhost:8080/user/update/'+this.props.id;
                    let newUser = { "username": this.props.user.login,
                      "password": this.props.user.password,
                      "mail": this.props.mailAddress,
                      "roles": {"idRole":56,"role":"UTILISATEUR"},//a changer
                      "activities": newActivities,
                      "regions": this.props.userRegions
                    }
                    axios.put(urlAddActivity, newUser).then(res => console.log());
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
            <button onClick={this.removeActivityToList.bind(this)}>-</button>
            <button onClick={this.addActivityToList.bind(this)}>+</button>
            </div>
    }
}

const ConnectedActivityList = connect(mapStateToProps, mapDispatchToProps)(ActivityList);

export {
    ConnectedActivityList
}
