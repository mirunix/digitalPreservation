app.controller('chartController', function($scope, $http) {
    $scope.headingTitle = "Chart";
    var stations;


    var layout = {

        xaxis: {
            title: 'Longitude'
        },

        yaxis: {
            title: 'Latitude',

        },

        title:'Metro Stations Vienna',

        width: 500,
        height: 500
    };



    $http.get('http://localhost:8080/getStationsVienna').
    then(function(response) {
        stations = response.data;

        var trace1 = {
            x: [],
            y: [],
            mode: 'markers',
            type: 'scatter'
        };


        for (var i = 0; i < stations.length; i++) {
            trace1.x.push(stations[i].longitude);
            trace1.y.push(stations[i].latitude);
        }

        var data = [ trace1 ];

        Plotly.newPlot('viennaChart', data, layout);

    });

    $http.get('http://localhost:8080/getStationsBudapest').
    then(function(response) {
        stations = response.data;

        var trace1 = {
            x: [],
            y: [],
            mode: 'markers',
            type: 'scatter'
        };


        for (var i = 0; i < stations.length; i++) {
            trace1.x.push(stations[i].longitude);
            trace1.y.push(stations[i].latitude);
        }

        var data = [ trace1 ];

        layout.title = 'Metro Stations Budapest';
        Plotly.newPlot('budapestChart', data, layout);

    });




});