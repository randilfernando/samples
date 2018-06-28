const dashboardPage = (req, res) => {
  res.render('dashboard', {title: 'Dashboard', user: req.user});
};

module.exports = {
    dashboardPage: dashboardPage
};