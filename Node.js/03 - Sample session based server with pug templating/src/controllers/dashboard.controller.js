const dashboardPage = (req, res) => {
    /**
     * @description in here when there is a session available it will deserialize session and add it to the request
     * here we configured deserializer to get user and append it
     * so the if there is a session every request should contain a req.user
     */
    res.render('dashboard', {title: 'Dashboard', user: req.user});
};

module.exports = {
    dashboardPage: dashboardPage
};