function getDateFromString(strDate) {
    var regex = /(\d{4})-(\d{2})-(\d{2})*/;
    var dateArray = regex.exec(strDate);
    return dateArray[3] + '.' + dateArray[2] + '.' + dateArray[1];
}
