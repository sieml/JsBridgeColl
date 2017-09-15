
var JSPlugin = new function() {

       this.showToast = function(msg) {
             JSBridgePlugin.showToast(msg);
       };

       this.showDialog=function(msg) {
             JSBridgePlugin.showDialog(msg);
       };

       this.withCallback=function(msg) {
             JSBridgePlugin.withCallback(msg, 'just string');
       };

};

function JSPluginCallbackHandler(data){
    alert(data);
};