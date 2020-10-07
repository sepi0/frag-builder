import React from 'react'

class LoginForm extends React.Component {
	constructor() {
		super();

		this.state = {
			username: "",
			password: ""
		}
	}

	render() {
		return (
			<div>
				<form>
					<label>username</label>
					<input type="text" name="username" />
					<label>password</label>
					<input type="password" name="password" />
					<input type="submit" name="Submit" />
				</form>
			</div>
		)
	}
}

export default LoginForm