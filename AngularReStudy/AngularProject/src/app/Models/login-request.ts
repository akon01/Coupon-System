export class LoginRequest {
  private name: string;
  private password: string;
  private type: string;

  setType(type: string) {
    this.type = type;
  }

  getType() {
    return this.type;
  }

  setName(userName) {
    this.name = userName;
  }

  getName() {
    return this.name;
  }

  setPassword(password) {
    this.password = password;
  }

  getPassword() {
    return this.password;
  }
}
