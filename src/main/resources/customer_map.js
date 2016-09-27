function () {
    for (var i = 0; i < this.contacts.length; i++) {
        emit(this.contacts[i].name, 1);
    }
}