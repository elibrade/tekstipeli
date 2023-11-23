package tekstipeli

trait Battle:
  def attack: String
  def escape: String
  def chat: String
  def help: String
  def statusCheck: String
  def quit: String
end Battle