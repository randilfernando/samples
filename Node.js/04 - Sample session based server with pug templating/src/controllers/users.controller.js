const User = require('../models/user.model').get();

const addUserPage = (req, res) => {
    res.render('user', {action: '/user/add'})
};

const addUser = async (req, res) => {
    delete req.body.id;
    const user = await User.create(req.body, {isNewRecord: true});
    res.redirect(`/user/get?id=${user.id}`);
};

const getUser = async (req, res) => {
    const user = await User.findById(req.query.id);

    if (user) {
        res.render('user', {user: user});
    } else {
        res.render('error-404');
    }
};

module.exports = {
    getUser: getUser,
    addUserPage: addUserPage,
    addUser: addUser
};