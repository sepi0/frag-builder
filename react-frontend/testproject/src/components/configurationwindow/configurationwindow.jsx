import React from 'react';
import axios from 'axios'
import './configurationwindow.styles.css'
import Button from 'react-bootstrap/Button'
import InputGroup from 'react-bootstrap/InputGroup'
import FormControl from 'react-bootstrap/FormControl'

class ConfigurationWindow extends React.Component {

  componentTypes = ["cpu", "gpu", "mobo", "ram", "case", "psu", "hdd", "ssd", "cooling"]
  additionalComponents = ["gpu", "ram", "ssd", "hdd", "cooling"]

  constructor() {
    super()
    this.state = {
      fetchedComponents: [],
      selectedComponents: [],
      searchValue: [],
      phoneNumber: "",
      emailAddress: "",
      totalPrice: 0
    }
  }
  
  // fetch data from database
  fetchProducts = async (productType) => {
    const res = await axios.get(`http://localhost:8080/api/components/${productType}/${this.state.searchValue}`)
    const products = []
    res.data.map(item => products.push(item))
    this.setState({ fetchedComponents: products })
  }

  // CART OPERATIONS  
  calculateTotal = () => {
    let total = 0;
    this.state.selectedComponents.map(item => Math.ceil(total += item.price))
    this.setState({ totalPrice: total })
  }
  
  addToCart = (productModel) => {
    const cart = this.state.selectedComponents
    cart.push(productModel)
    cart.concat(this.state.selectedComponents)
    this.setState({ selectedComponents: cart }, () => this.calculateTotal())
  }

  removeFromCart = (productModel) => {
    const cart = this.state.selectedComponents
    const index = cart.indexOf(productModel)
    cart.splice(index, 1)
    this.setState({ selectedComponents: cart }, () => this.calculateTotal())
  }

  sendOrder = async () => {
    const orderId = (Math.floor(100000 + Math.random() * 900000)).toString();
    const json = JSON.stringify({ 
      orderId: orderId, 
      components: this.state.selectedComponents.map(item => item.id).toString(),
      email: this.state.emailAddress,
      phone: this.state.phoneNumber
    })
    const res = await axios.post("http://localhost:8080/order/send", json, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
    console.log(res.data.data)
  }

  // CAN BE REDUCED
  handleSearchValue = (event, productType) => {
    this.setState({ searchValue: event.target.value }, () => this.fetchProducts(productType))
  }

  handleEmailAddress = event => {
    this.setState({ emailAddress: event.target.value })
  }

  handlePhoneNumber = event => {
    this.setState({ phoneNumber: event.target.value })
  }

  renderProducts = () => {
    const elements = []
    this.state.fetchedComponents.map(item => elements.push(
      <div className="product">
        <p className="item-text" value={item.id} onChange={() => this.addToCart(item.model)}>{item.model.toUpperCase()}</p>
        <Button 
          variant="primary" 
          onClick={() => this.addToCart(item)}>
            {item.price} EUR
        </Button>
      </div>
    ))
    return elements
  }

  renderCart = () => {
    const cart = []
    this.state.selectedComponents.map(item => cart.push(
      <div className="cart" key={item.id}>
        <p className="item-text">{item.model.toUpperCase()}</p>
        <Button variant="danger" onClick={() => this.removeFromCart(item)}>X</Button>
      </div>
    ))
    return cart
  }

  render() {
    return (
      <div>
        <div className="outside-cart">
          <h2>Košík</h2>
          {this.renderCart()}
          <h2>CELKOVO: {this.state.totalPrice} EUR</h2>
          <div id="user-details">
            <InputGroup className="mb-3">
              <FormControl placeholder="email" onChange={this.handleEmailAddress}/>
              <FormControl placeholder="tel.číslo" onChange={this.handlePhoneNumber}/>
            </InputGroup>
            <Button variant="primary" onClick={this.sendOrder}>Odoslať Objednávku</Button>
          </div>
        </div>
        <h2>Hľadať</h2>        
        <InputGroup className="mb-3">
          {this.componentTypes.map((item, key) => (
            <FormControl
              className="outside-configuration-window" 
              key={key} 
              placeholder={`${item}`}
              onChange={(event) => this.handleSearchValue(event, item)} 
              name={item}>
            </FormControl>
          ))}
        </InputGroup>
        <h2>Výsledok vyhľadávania</h2>
        <div>
          {this.renderProducts()}
        </div>
      </div>
    )
  }
}

export default ConfigurationWindow