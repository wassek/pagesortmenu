window.initPageSortUpFunction = (i) => {
    
};

window.handlePageSortUpSelection = (path) => {

};

window.handleNewMainNodeLoadedPageSortUp = (path) => {

};

window.executePageSortUpFunction = (path) => {
  
  var requestURL = '/modules/org.jahia.modules.sort.page?up=1&path=' + path + "&ran=" + Math.random();
  var request = new XMLHttpRequest();
  request.open('GET', requestURL);
  
  request.onload = function () {
     //location.reload();
     DexV2.id('JahiaGxtRefreshSidePanelButton').trigger('click');
  };
  request.send();
  


   
};
  
window.handleNewMainNodeLoadedPageSortDown = (path) => {
   
};


window.initPageSortDownFunction = (i) => {
    
};

window.handlePageSortDownSelection = (path) => {

};

window.handleNewMainNodeLoadedPageSortDown = (path) => {

};
       
window.executePageSortDownFunction = (path) => {

  var requestURL = '/modules/org.jahia.modules.sort.page?path=' + path + "&ran=" + Math.random();
  var request = new XMLHttpRequest();
  request.open('GET', requestURL);
    request.onload = function () {
     //location.reload();
     DexV2.id('JahiaGxtRefreshSidePanelButton').trigger('click');
  };
  request.send();
  

};
  
window.handleNewMainNodeLoadedPageSortDown = (path) => {
   
};