import React from 'react';
import { connect } from "react-redux";
import { addUserRegion, getUserRegions } from '../actions/actions'
const axios = require('axios');

const mapDispatchToProps = dispatch => {
    return {
        addUserRegion: region => dispatch(addUserRegion(region)),
        getUserRegions : regions => dispatch(getUserRegions(regions))
    };
  };

const mapStateToProps = (state) => {
    return {
        id: state.id,
        role: state.role,
        user: state.user,
        mailAddress: state.mailAddress,
        userRegions: state.regionList,
        allRegions: state.availableRegions,
        userActivities: state.activityList
    }
}

class RegionList extends React.Component {

    constructor(props) {
        super(props);
        this.state = { selectedRegion: '' };
    }

    handleChangeSelectedRegion(event) {
        this.setState({selectedRegion: event.target.value});
    }

    displayUserRegions = () => {
        var Data = this.props.userRegions, MakeItem = function(X) {
            return <li key={X.city}>{X.city}</li>
        };
        return Data.map(MakeItem);
    }

    displayAllRegions = () => {
        var Data = this.props.allRegions, MakeItem = function(X) {
              return <option key={X.city}>{X.city}</option>
        };
        return Data.map(MakeItem);
    }

    removeRegionToList = () => {
        for(let i=0;i<this.props.allRegions.length;i++){
            if(this.props.allRegions[i].city===this.state.selectedRegion){
                let boolContain=false;
                let newRegions = [];//on construit la nouvelle liste dde régions de l'utilisateur
                for(let j=0;j<this.props.userRegions.length;j++){
                    //vérifie si la région sélectionnée appartient à la liste de l'utilisateur
                    if(JSON.stringify(this.props.userRegions[j])===JSON.stringify(this.props.allRegions[i])){
                        boolContain=true;
                    }else{
                        newRegions.push(this.props.userRegions[j]);
                    }
                }
                if(boolContain){
                    this.props.getUserRegions(newRegions)

                    let urlRemoveRegion = 'http://localhost:8080/user/update/'+this.props.id;
                    let newUser = { "username": this.props.user.login,
                      "password": this.props.user.password,
                      "mail": this.props.mailAddress,
                      "roles": this.props.role,
                      "activities": this.props.userActivities,
                      "regions": newRegions
                    }
                    axios.put(urlRemoveRegion, newUser).then(res => console.log());
                }else{
                    alert("cette région ne fait pas partie de votre liste d'activitées");
                }
            }
        }
    }

    addRegionToList = () => {
        for(let i=0;i<this.props.allRegions.length;i++){
            if(this.props.allRegions[i].city===this.state.selectedRegion){
                let boolContain=false;
                for(let j=0;j<this.props.userRegions.length;j++){
                    //vérifie si la région sélectionnée n'appartient pas à la liste de l'utilisateur
                    if(JSON.stringify(this.props.userRegions[j])===JSON.stringify(this.props.allRegions[i])){
                        alert("cette région fait déjà partie de votre liste d'activitées");
                        boolContain=true;
                    }
                }
                if(!boolContain){
                    let newRegions = this.props.userRegions;
                    this.props.addUserRegion(this.props.allRegions[i])
                    newRegions.push(this.props.allRegions[i]);

                    let urlAddRegion = 'http://localhost:8080/user/update/'+this.props.id;
                    let newUser = { "username": this.props.user.login,
                      "password": this.props.user.password,
                      "mail": this.props.mailAddress,
                      "roles": this.props.role,
                      "activities": this.props.userActivities,
                      "regions": newRegions
                    }
                    axios.put(urlAddRegion, newUser).then(res => console.log());
                }
            }
        }
    }

    render() {
        return <div>
            <h3>Liste des régions</h3>
            <ul>{this.displayUserRegions()}</ul>
            <select onChange={this.handleChangeSelectedRegion.bind(this)}><option></option>{this.displayAllRegions()}</select>
            <button onClick={this.removeRegionToList.bind(this)}>-</button>
            <button onClick={this.addRegionToList.bind(this)}>+</button>
            </div>
    }
}

const ConnectedRegionList = connect(mapStateToProps, mapDispatchToProps)(RegionList);

export {
    ConnectedRegionList
}