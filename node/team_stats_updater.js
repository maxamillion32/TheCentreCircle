/*
    Work of Charles Waite
    Plymouth University
*/

var premTeams = ["9002", "9240", "9406", "9259", "9427", "9260", "9363",
"9378", "9249", "9092", "9426", "9158", "9053", "9423", "9387", "9127", 
"9296", "9384", "9287", "9008"];

var otherTeams = ["15702", "15692", "16110", "16270", 
"15934", "16175", "15679", "16050", "16006", "15999", "16117", "16009", 
"16107", "16261", "16040", "16098", "16025", "16017", "16180", "16043",
"10285", "10303", "10437", "10576", "10307"];

var teams2 = ["10281", "10388", "10653",
"10476", "10453", "10646", "10419", "10329", "10677", "10269", "10442",
"10347", "10423", "6468", "6454", "6319", "6299", "6426", "6503", "6461",
"20598", "6463", "6372", "6752", "6776", "6740", "6844", "6775", "6930",
"6894"];

var teams3 = ["6751", "6813", "6833", "6829", "6856", "6882", "6850", "6923",
"6839", "8649", "8618", "8559", "8607", "8599", "8482", "8605", "8529",
"8631", "8504", "8477", "8668", "8580", "8661", "8593", "8469", "9072",
"9065", "9274", "9221", "9133", "9349", "9083", "9227", "9039", "9317",
"9318", "9446", "9238"];

var teams4 = ["9044", "9297", "9220", "9324", "9059", "9066",
"9333", "9175", "9277", "9088", "9048", "10061", "10020", "10033", "10040",
"10122", "9883", "10085", "10031", "10004", "9831", "9865", "10042", "10007",
"9875", "9973", "10024", "10073", "9966", "10134", "10140"];

var englishChampionshipTeams = ["9072", "9274", "9065", "9221", "9133", "9349",
"9227", "9083", "9059", "9039", "9317", "9318", "9238", "9446", "9044", "9297",
"9324", "9066", "9220", "9175", "9333", "9088", "9277", "9048"];

var serieATeams = ["11922", "11947", "11998", "11917", "11894", "12013",
"11938", "11925", "11850", "11903", "12046", "11867", "11811", "11822",
"12005", "12051", "11958", "11837", "11898", "11914"];

var demoTeams = ["14796", "14792"];

var http = require('http');
var firebase = require('firebase')
var ref = new Firebase('https://cwprco304.firebaseio.com');

for(var i = 0; i < demoTeams.length; i++) {

    var options = {
      "method": "GET",
      "hostname": "api.football-api.com",
      "port": null,
      "path": "/2.0/team/"+ demoTeams[i] + "?Authorization=565ec012251f932ea4000001465e5017e24b4c3f49c5f59207d768b3"
    };

    var req = http.request(options, function (res) {
      var content = "";

      res.on("data", function (chunk) {
        content += chunk;
      });

      res.on("end", function () {
        var data = JSON.parse(content);
        console.log(data);
        if (data.status != "error") {
          var teamRef = ref.child("teams/" + data.team_id);
          var teamObj = {};
          console.log(data.leagues);
          var mainLeague = data.leagues.split(',');
          data.leagues = mainLeague[0];
          console.log(data.name);
          teamObj = data;

          teamRef.set(teamObj);
          console.log(data.name);
        }
        

      });


    });

    req.on('error', function(error) {
        console.log("Error while calling endpoint.", error);
    });

    req.end();
}
